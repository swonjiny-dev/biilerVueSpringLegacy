<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="10" >
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>회원가입</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn color="red" type="summit" :disabled="!valid">회원가입</v-btn>
          </v-toolbar>
          <v-form v-model="valid" ref="form" @submit.prevent="onSubmit">
            <v-card-text>
              <v-text-field
                v-model="userId"
                label="아이디"
                name="userId"
                prepend-icon="mdi-account"
                type="text"
                :rules="userIdRules"
                required
              />
              <v-text-field
                v-model="userPw"
                label="비밀번호"
                name="password"
                prepend-icon="mdi-onepassword"
                type="password"
                :rules="passwordRules"
                required
              />
              <v-text-field
                v-model="userPwCk"
                label="비밀번호확인"
                name="userPwCk"
                prepend-icon="mdi-onepassword"
                type="text"
                :rules="pwCheckRules"
                required
              />
              <v-text-field
                v-model="userName"
                label="사용자이름"
                name="userName"
                prepend-icon="mdi-account"
                type="text"
                :rules="userNameRules"
                required
              />
              
            
            </v-card-text>
          </v-form>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

export default {
  data() {
    return {
      rPath : '/',
      userId : '',
      userPw : '',
      userName : '',
      userPwCk : '',
      valid : false,
      userIdRules: [
          v => !!v || '아이디 필수입니다.',
      ],
      userNameRules: [
          v => !!v || '사용자이름은 필수입니다.',
      ],
      passwordRules: [
          v => !!v || '비밀번호는 필수입니다.',
      ],
      pwCheckRules: [
          v => !!v || '비밀번호확인은 필수입니다.',
          v => v === this.userPw || '비밀번호가 일치하지 않습니다.',
      ],
    }
  }, 
  created() {
    // 로그인후 해당페이지로 이동
    this.rPath = this.$route.query.rPath;
    if(!this.rPath) this.rPath = "/";

    document.title = '회원가입';
  },
  methods: {
    onSubmit(){          
      if(this.$refs.form.validate()){
        console.log('회원가입');
        
      }
    }
  },
     
}
</script>

<style>

</style>