<template>
	<div>
		<div v-if="!this.$store.state.loginRegister.isLogin" class="login-register">
			<div class="contain">
				<div class="big-box" :class="{active:isLogin}">
					<div class="big-contain" v-if="isLogin">
						<div class="btitle">账户登录</div>
						<div class="bform">
							<input type="email" placeholder="邮箱" v-model="form.useremail" @keyup.enter="login">
							<span class="errTips" v-if="emailError">* 邮箱填写错误 *</span>
							<input type="password" placeholder="密码" v-model="form.userpwd" @keyup.enter="login">
							<span class="errTips" v-if="emailError">* 密码填写错误 *</span>
						</div>
						<button class="bbutton" @click="login">登录</button>
					</div>
					<div class="big-contain" v-else>
						<div class="btitle">创建账户</div>
						<div class="bform">
							<input type="text" placeholder="用户名" v-model="form.username" @keyup.enter="register">
							<span class="errTips" v-if="existed">* 用户名已经存在！ *</span>
							<input type="email" placeholder="邮箱" v-model="form.useremail" @keyup.enter="register">
							<input type="password" placeholder="密码" v-model="form.userpwd" @keyup.enter="register">
						</div>
						<button class="bbutton" @click="register">注册</button>
					</div>
				</div>
				<div class="small-box" :class="{active:isLogin}">
					<div class="small-contain" v-if="isLogin">
						<div class="stitle">你好，朋友!</div>
						<p class="scontent">开始注册，和我们一起旅行</p>
						<button class="sbutton" @click="changeType">注册</button>
					</div>
					<div class="small-contain" v-else>
						<div class="stitle">欢迎回来!</div>
						<p class="scontent">与我们保持联系，请登录你的账户</p>
						<button class="sbutton" @click="changeType">登录</button>
					</div>
				</div>
			</div>
		</div>
		<div v-if="this.$store.state.loginRegister.isLogin" class="user-info-wrapper">
			<div class="user-info">
				<div class="user-info-item"><span class="decoration">昵称</span><input type="text" v-model="userMessage.username" disabled></div>
				<div class="user-info-item"><span class="decoration">真名</span><input type="text" v-model="userMessage.realname"></div>
				<div class="user-info-item"><span class="decoration">性别</span><input type="text" v-model="userMessage.sex"></div>
				<div class="user-info-item"><span class="decoration">地址</span><input type="text" v-model="userMessage.addr"></div>
				<div class="user-info-item"><span class="decoration">身份证</span><input type="text" v-model="userMessage.identification"></div>
				<div class="user-info-item"><span class="decoration">电话</span><input type="text" v-model="userMessage.phone"></div>
				<button class="btn" @click="btnClick()">提交</button>
			</div>
		</div>
	</div>
</template>

<script>
import { request } from "@/network/request.js";  // 网络请求相关

	export default{
		name:'login-register',
		data(){
			return {
				isLogin:true,
				emailError: false,
				passwordError: false,
				existed: false,
				form:{
					username:'',
					useremail:'',
					userpwd:''
				},
				userMessage: {}	// 用户个人信息
			}
		},
		methods:{
			changeType() {
				this.isLogin = !this.isLogin
				this.form.username = ''
				this.form.useremail = ''
				this.form.userpwd = ''
			},
			btnClick() {
				console.log('点击了提交');
				console.log(this.userMessage);
				request({
					method: 'post',
					url: '/login/user',
					data: {
						userinfo: this.userMessage
					}
				}).then( (res) => {
					console.log(res.data);
					if(res.data == '修改成功') {
						alert(res.data)
					}
				}).catch(() => {
					console.log('修改失败');
				})
			},
			login() {
				const self = this;
				if (self.form.useremail != "" && self.form.userpwd != "") {
					request({
						method:'post',
						url: '/login',
						data: {
							email: self.form.useremail,
							password: self.form.userpwd
						}
					})
					.then( res => {
						// console.log(res.data)
						this.userMessage = res.data
						switch(res.data.result){
							case '0': 
								alert("登录成功！");
								this.$store.commit('login', res.data);
								// this.$router.push('/')
								break;
							case '-1':
								this.emailError = true;
								break;
							case '1':
								this.passwordError = true;
								break;
						}
					})
					.catch( err => {
						console.log(err);
					})
				} else{
					alert("填写不能为空！");
				}
			},
			register(){
				const self = this;
				if(self.form.username != "" && self.form.useremail != "" && self.form.userpwd != ""){
					request({
						method:'post',
						url: '/register',
						data: {
							username: self.form.username,
							email: self.form.useremail,
							password: self.form.userpwd
						}
					})
					.then( res => {
						switch(res.data){
							case 0:
								alert("注册成功！");
								this.login();
								break;
							case -1:
								this.existed = true;
								break;
						}
					})
					.catch( err => {
						console.log(err);
					})
				} else {
					alert("填写不能为空！");
				}
			}
    }
	}
</script>

<style scoped>
	.login-register{
		width: 100vw;
		height: 100vh;
		box-sizing: border-box;
	}
	.contain{
		width: 60%;
		height: 60%;
		margin-top: -50px;
		position: relative;
		top: 50%;
		left: 50%;
		transform: translate(-50%,-50%);
		background-color: #fff;
		border-radius: 20px;
		box-shadow: 0 0 3px #f0f0f0,
					0 0 6px #f0f0f0;
	}
	.big-box{
		width: 70%;
		height: 100%;
		position: absolute;
		top: 0;
		left: 30%;
		transform: translateX(0%);
		transition: all 1s;
	}
	.big-contain{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.btitle{
		font-size: 1.5em;
		font-weight: bold;
		color: rgb(210,0,0);
	}
	.bform{
		width: 100%;
		height: 40%;
		padding: 2em 0;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		align-items: center;
	}
	.bform .errTips{
		display: block;
		width: 50%;
		text-align: left;
		color: red;
		font-size: 0.7em;
		margin-left: 1em;
	}
	.bform input{
		width: 50%;
		height: 30px;
		border: none;
		outline: none;
		border-radius: 10px;
		padding-left: 2em;
		background-color: #f0f0f0;
	}
	.bbutton{
		width: 20%;
		height: 40px;
		border-radius: 24px;
		border: none;
		outline: none;
		background-color: rgb(210,0,0);
		color: #fff;
		font-size: 0.9em;
		cursor: pointer;
	}
	.small-box{
		width: 30%;
		height: 100%;
		background: linear-gradient(135deg,rgb(210,0,0),rgb(216, 163, 16));
		/* background: linear-gradient(135deg,rgb(57,167,176),rgb(56,183,145)); */
		position: absolute;
		top: 0;
		left: 0;
		transform: translateX(0%);
		transition: all 1s;
		border-top-left-radius: inherit;
		border-bottom-left-radius: inherit;
	}
	.small-contain{
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.stitle{
		font-size: 1.5em;
		font-weight: bold;
		color: #fff;
	}
	.scontent{
		font-size: 0.8em;
		color: #fff;
		text-align: center;
		padding: 2em 4em;
		line-height: 1.7em;
	}
	.sbutton{
		width: 60%;
		height: 40px;
		border-radius: 24px;
		border: 1px solid #fff;
		outline: none;
		background-color: transparent;
		color: #fff;
		font-size: 0.9em;
		cursor: pointer;
	}
	
	.big-box.active{
		left: 0;
		transition: all 0.5s;
	}
	.small-box.active{
		left: 100%;
		border-top-left-radius: 0;
		border-bottom-left-radius: 0;
		border-top-right-radius: inherit;
		border-bottom-right-radius: inherit;
		transform: translateX(-100%);
		transition: all 1s;
	}
.user-info-wrapper {
  width: 100%;
  height: 500px;
}
.user-info-wrapper .user-info {
  width: 60%;
  height: 500px;
  background: linear-gradient(135deg,rgb(210,0,0),rgb(216, 163, 16));
  margin: 100px auto ;
  overflow: hidden;
  border: 2px solid rgb(175, 0, 0);
  border-radius: 20px;
  box-shadow: 0 0 3px rgb(175, 0, 0),
      0 0 6px rgb(175, 0, 0);
  text-align: center;
}
.user-info-wrapper .user-info .user-info-item {
  width: 45%;
  float: left;
  margin: 55px auto;
  margin-right: 5%;
}
.user-info-wrapper .user-info .decoration {
  display: inline-block;
  width: 20%;
  text-align: center;
}
.user-info-wrapper .user-info input {
  width: 80%;
  height: 30px;
  box-sizing: border-box;
  border: none;
  outline: none;
}
.user-info-wrapper .btn {
  width: 20%;
  height: 40px;
  border-radius: 24px;
  border: none;
  outline: none;
  background-color: rgb(210,0,0);
  color: #fff;
  font-size: 0.9em;
  cursor: pointer;
}
.user-info-wrapper .btn:hover {
  background-color: rgb(175,0,0);
}
</style>
