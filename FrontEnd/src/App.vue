<template>
	<v-app>
		<router-view></router-view>
    
	</v-app>
</template>

<script>


export default {
  name: 'App',

  computed: {
  

  },
  data: () => ({
  
  }),
  methods: {

  },
};
</script>
<style>
  /* 트랜젝션이용시 사용 */
  .list-item {
    display: inline-block;
    margin-right: 10px;
  }
  .list-enter-active, .list-leave-active {
    transition: all 1s;
  }
  .list-enter, .list-leave-to /* .list-leave-active below version 2.1.8 */ {
    opacity: 0;
    transform: translateY(30px);
  }
</style>
