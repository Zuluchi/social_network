package application.controllers;

import application.models.Comment;
import application.models.CommentDto;
import application.models.PostDto;
import application.requests.CommentRequest;
import application.responses.GeneralListResponse;
import application.responses.GeneralResponse;
import application.service.FeedsService;
import application.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:8086", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final FeedsService feedsService;

    @GetMapping
    public ResponseEntity<GeneralListResponse<PostDto>> searchPosts(@RequestParam(value = "text") String text,
                                                                    @RequestParam(value = "date_from", required = false) Long dateFrom,
                                                                    @RequestParam(value = "date_to", required = false) Long dateTo) {
        return ResponseEntity.ok(feedsService.getFeed());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<PostDto>> getPost(@PathVariable int id) {
        return ResponseEntity.ok(postsService.getPostResponse(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<GeneralListResponse<CommentDto>> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(postsService.getCommentsResponse(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<GeneralResponse<Comment>> postComment(@PathVariable Integer id,
                                                                @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postsService.setComment(id, commentRequest));
    }

}
