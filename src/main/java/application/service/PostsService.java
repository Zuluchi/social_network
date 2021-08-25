package application.service;

import application.dao.*;
import application.models.*;
import application.models.dto.*;
import application.models.requests.CommentRequest;
import application.models.requests.LikeRequest;
import application.models.requests.PostRequest;
import application.models.requests.TagRequest;
import application.models.responses.GeneralListResponse;
import application.models.responses.GeneralResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final DaoPost daoPost;
    private final DaoPerson daoPerson;
    private final DaoComment daoComment;
    private final DaoLike daoLike;
    private final DaoTag daoTag;
    private String subCommentParentId;

    public PostDto getPostDto(int postId) {

        Post post = daoPost.getById(postId);
        int likes = daoLike.getCountLike(postId);
        Person person = daoPerson.getById(post.getAuthorId());
        PersonDto author = PersonDto.fromPerson(person);
        List<CommentDto> comments = getComments(postId);
        List<String> tags = daoTag.getTagsByPostId(postId);

        return PostDto.fromPost(post, likes, author, comments, tags);
    }

    public List<CommentDto> getComments(Integer postId) {
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (Comment comment : daoComment.getCommentsByPostId(postId)) {
            Person person = daoPerson.getById(comment.getAuthorId());
            List<CommentDto> subCommentList = getSubComments(comment.getId());
            CommentDto commentDto = CommentDto.fromComment(comment, person, subCommentList);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }

    public List<CommentDto> getSubComments(Integer parentId) {
        List<Comment> subComments = daoComment.getSubComment(parentId);
        List<CommentDto> subCommentsList = new ArrayList<>();

        if(subComments.size() > 0) {
            for(Comment subComment : subComments) {
                Person person = daoPerson.getById(subComment.getAuthorId());
                CommentDto commentDto = CommentDto.fromComment(subComment, person, null);
                subCommentsList.add(commentDto);
            }
        }
        return subCommentsList;
    }


    public GeneralListResponse<CommentDto> getSubCommentsResponse() {

        return new GeneralListResponse<>(getComments(Integer.valueOf(subCommentParentId)));
    }

    public GeneralResponse<PostDto> getPostResponse(int postId) {
        return new GeneralResponse<>(getPostDto(postId));
    }

    public GeneralListResponse<CommentDto> getCommentsResponse(Integer postId) {

        return new GeneralListResponse<>(getComments(postId));
    }

    public GeneralResponse<CommentDto> setComment(String postId, CommentRequest commentRequest) {
        Comment postComment = new Comment();
        postComment.setParentId(commentRequest.getParent_id());
        postComment.setCommentText(commentRequest.getComment_text());
        if(postId.equals("undefined")) {
            postId = String.valueOf(daoComment.getPostIdByCommentId(commentRequest.getParent_id()));
            subCommentParentId = postId;
        }
        postComment.setPostId(Integer.valueOf(postId));
        postComment.setTime(System.currentTimeMillis());
        Person currentPerson = daoPerson.getAuthPerson();
        postComment.setAuthorId(currentPerson.getId());
        daoComment.save(postComment);
        CommentDto commentDto = CommentDto.fromComment(postComment, currentPerson, getSubComments(commentRequest.getParent_id()));
        return new GeneralResponse<>(commentDto);
    }

    public GeneralResponse<CommentDto> editComment(CommentRequest request, int id, int comment_id) {
        Comment postComment = daoComment.getById(comment_id);
        postComment.setCommentText(request.getComment_text());
        postComment.setParentId(request.getParent_id());
        daoComment.update(postComment);
        CommentDto commentDto = CommentDto.fromComment(postComment,daoPerson.getAuthPerson(), getSubComments(request.getParent_id()));
        return new GeneralResponse<>(commentDto);
    }

    public GeneralResponse<LikeResponseDto> getLikes(int itemId, String type) {
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        List<String> userList = daoLike.getUsersLike(itemId);
        likeResponseDto.setUsers(userList);
        likeResponseDto.setLikes(String.valueOf(userList.size()));
        return new GeneralResponse<>(likeResponseDto);
    }

    public GeneralResponse<Map<String, Boolean>> getLiked(int user_id, int itemId, String type) {
        Map<String, Boolean> isLiked = new HashMap<>();
        List<String> usersList = daoLike.getUsersLike(itemId);
        isLiked.put("likes", usersList.contains(String.valueOf(user_id)));

        return new GeneralResponse<>(isLiked);
    }

    public GeneralResponse<LikeResponseDto> setLikes(LikeRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person currentPerson = daoPerson.getByEmail(authentication.getName());
        if(!getLiked(currentPerson.getId(), request.getItem_id(), request.getType()).getData().get("likes")) {

            Like like = new Like();
            like.setPostId(request.getItem_id());
            like.setTime(System.currentTimeMillis());
            like.setPersonId(currentPerson.getId());
            daoLike.save(like);
        }
        LikeResponseDto likeResponseDto = new LikeResponseDto();
        List<String> userList = daoLike.getUsersLike(request.getItem_id());
        likeResponseDto.setUsers(userList);
        likeResponseDto.setLikes(String.valueOf(userList.size()));

        return new GeneralResponse<>(likeResponseDto);
    }

    public GeneralResponse<Map<String, String>> deleteLike(int itemId, String type) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person currentPerson = daoPerson.getByEmail(authentication.getName());
        daoLike.delete(itemId, currentPerson.getId());
        HashMap<String, String> deleteLikeResponse = new HashMap<>();
        deleteLikeResponse.put("likes", "1");
        return new GeneralResponse<>(deleteLikeResponse);
    }

    public GeneralListResponse<Tag> getTags() {
        return new GeneralListResponse<>(daoTag.getAll());
    }

    public GeneralResponse<Tag> setTag(TagRequest request) {
        Tag tag = new Tag();
        tag.setTag(request.getTag());
        daoTag.save(tag);
        //надо прикрепить к посту в tag2post
        return new GeneralResponse<>(tag);
    }

    public GeneralResponse<HashMap<String, String>> deleteTag(int tagId) {
        // Здесь ДАО метод удаления
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "ok");
        return new GeneralResponse<>(response);
    }

    public GeneralListResponse<PostDto> getPosts(String text, String author, Long dateFrom, Long dateTo) {

        val listPersonsId = daoPerson.getPersonsByFirstNameSurname(author)
                .stream()
                .map(Person::getId)
                .collect(Collectors.toList());

        val posts = listPersonsId.stream()
                .map(item -> getPosts(text, item, dateFrom, dateTo))
                .flatMap(List::stream).collect(Collectors.toList());

        return new GeneralListResponse<>(posts
                .stream()
                .map(item -> getPostDto(item.getId()))
                .collect(Collectors.toList()));
    }

    private List<Post> getPosts(String text, Integer authorId, Long dateFrom, Long dateTo) {
        return daoPost.getPosts(text, authorId, dateFrom, dateTo);
    }

    public GeneralResponse<PostDto> editPost(PostRequest request, int postId) {
        Post post = daoPost.getById(postId);
        post.setPostText(request.getPostText());
        post.setTitle(request.getTitle());
        daoPost.update(post);
        return new GeneralResponse<>(getPostDto(postId));
    }

    public GeneralResponse<MessageRequestDto> deletePost(int id) {
        daoPost.deleteByPostId(id);
        return new GeneralResponse<>(new MessageRequestDto("ok"));
    }
}
