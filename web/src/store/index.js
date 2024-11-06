import Vue from 'vue'
import Vuex from 'vuex'

// 1.安装插件
Vue.use(Vuex)

// 2.创建对象
const store = new Vuex.Store({
    state: {
      // 状态（变量）
      addr: '哈尔滨市', // 存放选择的城市
      area: '香坊区', // ，默认选择的区
      areas: ['香坊区','南岗区','道里区','道外区','松北区','呼兰区','平方区'],
      visible: false, // 城市切换组件是否显示，默认不显示
      loginRegister: {
        username: '请登录',
        isManager: false,
        isLogin: false, // 登陆状态，默认未登录
      },
      searchArea: ''
    },
    mutations: {
      // 方法
      setVisible(state) { // 通过修改visible的值实现 选择城市 框的显示与隐藏
        state.visible = !state.visible
      },
      changeAddr(state, cityInfo) { // 修改选择的城市
        state.addr = cityInfo.newAddr
        // state.addr = cityInfo.newAddr
        state.areas = cityInfo.areas
        // for(let item of areas) {
        //   // state.areas.push(item)
        //   console.log(item);
        // }
      },
      login(state, userinfo) {
        // console.log(userinfo);
        state.loginRegister.username = userinfo.username
        if(userinfo.manager == '0') {
          state.loginRegister.isManager = false
        } else {
          state.loginRegister.isManager = true
          console.log(state.loginRegister.isManager);
        }
        state.loginRegister.isLogin = true  // 将登陆状态设置为true
      },
      search(state, addr) {
        state.searchArea = addr
        // router.push({path: '/new_house'})
      }
    },
    actions: {
    // 处理异步操作
    },
    getters: {
    // 类似于计算属性
    },
    modules: {
    // 划分模块
    }
})

// 3.导出sotre对象
export default store