webpackJsonp([21],{GRex:function(e,t){},"N9p+":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var s=i("Dd8w"),n=i.n(s),r=(i("IcnI"),i("NYxO")),a=i("UBpT"),d=i("CqtB"),l={name:"FriendsApplication",computed:n()({},Object(r.c)("profile/friends",["getResult"]),{requestFriends:function(){return this.getResult}}),methods:n()({},Object(r.b)("profile/friends",["apiAddFriends","apiRequest"])),mounted:function(){0===this.requestFriends.length&&this.apiRequest()}},c={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return e.requestFriends.request.length>0?i("div",{staticClass:"friends-application"},[i("ul",{staticClass:"friends-application__list"},e._l(e.requestFriends.request,function(t){return i("li",{key:t.id,staticClass:"friends-application__item"},[i("div",{staticClass:"friends-application__pic"},[i("img",{attrs:{src:t.photo,alt:t.first_name}})]),i("router-link",{staticClass:"friends-application__name",attrs:{to:{name:"ProfileId",params:{id:t.id}}}},[e._v(e._s(t.first_name+" "+t.last_name))]),i("a",{staticClass:"friends-application__link",attrs:{href:"#"},on:{click:function(i){return i.preventDefault(),e.apiAddFriends(t.id)}}},[e._v("Добавить")])],1)}),0)]):e._e()},staticRenderFns:[]};var f=i("VU/8")(l,c,!1,function(e){i("GRex")},null,null).exports,o={name:"Friends",components:{FriendsPossible:a.a,FriendsBlock:d.a,FriendsRequest:f},data:function(){return{first_name:""}},computed:n()({},Object(r.c)("profile/friends",["getResultById"]),Object(r.c)("profile/friends",["getResult"]),{friends:function(){return this.getResultById}}),methods:n()({},Object(r.b)("profile/friends",["apiFriends"])),beforeRouteEnter:function(e,t,i){i(function(e){e.apiFriends()})}},u={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"friends inner-page"},[i("div",{staticClass:"inner-page__main"},[i("div",{staticClass:"friends__header"},[e.friends.first_name&&e.friends.first_name?[i("h2",{staticClass:"friends__title"},[e._v("Мои друзья")])]:[i("h2",{staticClass:"friends__title"},[e._v("Друзей нет")])],i("div",{staticClass:"friends__search"},[i("div",{staticClass:"friends__search-icon"},[i("simple-svg",{attrs:{filepath:"/static/img/search.svg"}})],1),i("input",{directives:[{name:"model",rawName:"v-model",value:e.first_name,expression:"first_name"}],staticClass:"friends__search-input",attrs:{placeholder:"Начните вводить имя друга..."},domProps:{value:e.first_name},on:{input:function(t){t.target.composing||(e.first_name=t.target.value)}}})])],2),i("div",{staticClass:"friends__list"},[e.friends.length||e.friends.info?e._l(e.friends.info,function(e){return i("friends-block",{key:e.info.id,attrs:{info:e}})}):e._e(),[i("h2",{staticClass:"friends__title"},[e._v("Заявки в друзья")]),i("friends-request")]],2)]),i("div",{staticClass:"inner-page__aside"},[i("friends-possible")],1)])},staticRenderFns:[]},_=i("VU/8")(o,u,!1,null,null,null);t.default=_.exports}});
//# sourceMappingURL=21.bbcb6aabafea313a1557.js.map