<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="10">
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>로그인</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
          <v-form v-model="valid" ref="form" @submit.prevent="onSubmit">
            <v-text-field
              v-model="userId"
              label="아이디"
              name="userId"
              prepend-icon="mdi-account"
              type="text"
              :rules="userRules"
              required
            />
            <v-text-field
              v-model="userPw"
              id="password"
              label="비밀번호"
              name="password"
              prepend-icon="mdi-onepassword"
              type="password"
              :rules="passwordRules"
              required
              />
            <v-card-actions>
              <v-btn text color="primary" @click="singupEvent">회원가입</v-btn>
              <v-spacer />
              <v-btn color="primary" type="summit" :disabled="!valid">로그인</v-btn>
              </v-card-actions>
          </v-form>
          </v-card-text>
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
      valid : false,
      userRules: [
          v => !!v || '아이디 필수입니다.',
      ],
      passwordRules: [
          v => !!v || '비밀번호는 필수입니다.',
      ],
    }
  }, 
  created() {
    // 로그인후 해당페이지로 이동
    this.rPath = this.$route.query.rPath;
    if(!this.rPath) this.rPath = "/";
  },
  methods: {
    singupEvent(){
      this.$router.push('/signUp');
    },
    onSubmit(){          
      console.log(1);    
    }
  },
     
}
</script>

<style>

</style>