webpackJsonp([12],{"5gTm":function(e,t){},TwRG:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("Dd8w"),n=i.n(a),r=i("NYxO"),o=i("+cKO"),s=i("TYx6"),l=i("/QaM"),d=i("mtWM"),u=i.n(d),c={name:"Login",components:{PasswordField:s.a,EmailField:l.a},data:function(){return{email:"",password:""}},computed:{redirectUrl:function(){return this.$route.query.redirect}},methods:n()({},Object(r.b)("auth/api",["login"]),Object(r.b)("profile/info",["apiInfo"]),{submitHandler:function(){var e=this;this.$v.$invalid?this.$v.$touch():(localStorage.removeItem("user-token"),delete u.a.defaults.headers.common.Authorization,this.login({email:this.email,password:this.password}).then(function(){e.apiInfo().then(function(){e.$router.push({name:e.redirectUrl||"News"})})}))}}),validations:{email:{required:o.required,email:o.email},password:{required:o.required,minLength:Object(o.minLength)(8)}}},m={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"login"},[i("h2",{staticClass:"login__title form__title"},[e._v("Войдите в аккаунт")]),i("form",{staticClass:"login__form",on:{submit:function(t){return t.preventDefault(),e.submitHandler(t)}}},[i("email-field",{attrs:{id:"login-email",v:e.$v.email},model:{value:e.email,callback:function(t){e.email=t},expression:"email"}}),i("password-field",{attrs:{id:"login-password",v:e.$v.password},model:{value:e.password,callback:function(t){e.password=t},expression:"password"}}),i("div",{staticClass:"login__action"},[i("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[e._v("Войти")]),i("router-link",{staticClass:"login__link",attrs:{to:{name:"Forgot"}}},[e._v("Забыли пароль?")])],1)],1)])},staticRenderFns:[]};var f=i("VU/8")(c,m,!1,function(e){i("5gTm")},"data-v-7868ed52",null);t.default=f.exports}});
//# sourceMappingURL=12.1bcfdb01b97ebe58e74b.js.map