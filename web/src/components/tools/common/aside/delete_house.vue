<template>
  <div>
    <div class="tab-wrapper">
      <ul class="tab-title">
        <li @click="index=0" :class="{active: index==0}">楼盘</li>  <!-- 楼盘tab按钮 -->
        <li @click="index=1" :class="{active: index==1}">新房</li>  <!-- 新房tab按钮 -->
        <li @click="index=2" :class="{active: index==2}">二手房</li>  <!-- 二手房tab按钮 -->
        <li @click="index=3" :class="{active: index==3}">租赁</li>  <!-- 租赁tab按钮 -->
      </ul>
      <div class="tab-content">
        <div class="tab-content-item" v-show="index==0" keep-alive> <!-- 楼盘tab内容 -->
          <table>
            <thead>
              <tr>
                <th>编号</th><th>省</th><th>市</th><th>区</th><th>价格</th><th>类型</th><th>售楼地址</th><th>户数</th><th>电话</th><th>项目名称</th><th>项目地址</th><th>开发商</th><th>展示图</th><th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in all_data.properties" :key="item.id">
                <th>{{ item.id }}</th>
                <td>{{ item.provice }}</td>
                <td>{{ item.city }}</td>
                <td>{{ item.area }}</td>
                <td>{{ item.prices }}</td>
                <td>{{ item.housetype }}</td>
                <td>{{ item.selladdr }}</td>
                <td>{{ item.housenum }}</td>
                <td>{{ item.phone }}</td>
                <td>{{ item.projname }}</td>
                <td>{{ item.projaddr }}</td>
                <td>{{ item.devpeople }}</td>
                <td>{{ item.picture }}</td>
                <td><button @click="deleteProjItem(item)">删除</button></td>
              </tr>
            </tbody>
          </table>
          <button class="btn" @click="projClick()">提交</button>
        </div>
        <div class="tab-content-item" v-show="index==1" keep-alive> <!-- 新房tab内容 -->
          <table>
            <thead>
              <tr>
                <th>编号</th><th>所属楼盘</th><th>户型</th><th>面积</th><th>楼层</th><th>价格</th><th>展示图</th><th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in all_data.new_houses" :key="item.id">
                <th>{{ item.hid }}</th>
                <td>{{ item.pid }}</td>
                <td>{{ item.type }}</td>
                <td>{{ item.harea }}</td>
                <td>{{ item.floor }}</td>
                <td>{{ item.price }}</td>
                <td>{{ item.hpicture }}</td>
                <td><button @click="deleteNewItem(item)">删除</button></td>
              </tr>
            </tbody>
          </table>
          <button class="btn" @click="newClick()">提交</button>
        </div>
        <div class="tab-content-item" v-show="index==2" keep-alive> <!-- 二手房tab内容 -->
          <table>
            <thead>
              <tr>
                <th>编号</th><th>卖点</th><th>图片</th><th>价格</th><th>户型</th><th>面积</th><th>朝向</th><th>小区</th><th>楼层</th><th>建筑年代</th><th>省</th><th>市</th><th>区</th><th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in all_data.old_houses" :key="item.id">
                <th>{{ item.oid }}</th>
                <td>{{ item.sellpoint }}</td>
                <td>{{ item.picture }}</td>
                <td>{{ item.price }}</td>
                <td>{{ item.type }}</td>
                <td>{{ item.narea }}</td>
                <td>{{ item.direction }}</td>
                <td>{{ item.xiaoqu }}</td>
                <td>{{ item.floor }}</td>
                <td>{{ item.year }}</td>
                <td>{{ item.provice }}</td>
                <td>{{ item.city }}</td>
                <td>{{ item.area }}</td>
                <td><button @click="deleteOldItem(item)">删除</button></td>
              </tr>
            </tbody>
          </table>
          <button class="btn" @click="oldClick()">提交</button>
        </div>
        <div class="tab-content-item" v-show="index==3" keep-alive> <!-- 租赁tab内容 -->
          <table>
            <thead>
              <tr>
                <th>编号</th><th>图片</th><th>价格</th><th>出租方式</th><th>户型</th><th>面积</th><th>朝向</th><th>楼层</th><th>楼层总数</th><th>装修程度</th><th>小区</th><th>地址</th><th>省</th><th>市</th><th>区</th><th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in all_data.rent_houses" :key="item.id">
                <th>{{ item.rid }}</th>
                <td>{{ item.picture }}</td>
                <td>{{ item.price }}</td>
                <td>{{ item.mode }}</td>
                <td>{{ item.type }}</td>
                <td>{{ item.narea }}</td>
                <td>{{ item.direction }}</td>
                <td>{{ item.floor }}</td>
                <td>{{ item.sumfloor }}</td>
                <td>{{ item.decoration }}</td>
                <td>{{ item.xiaoqu }}</td>
                <td>{{ item.addr }}</td>
                <td>{{ item.provice }}</td>
                <td>{{ item.city }}</td>
                <td>{{ item.area }}</td>
                <td><button @click="deleteRentItem(item)">删除</button></td>
              </tr>
            </tbody>
          </table>
          <button class="btn" @click="rentClick()">提交</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { request } from "@/network/request.js";  // 网络请求相关

export default {
  name: 'admin_delete',
  data() {
    return {
      index: 0,
      all_data: {
        properties: [],
        new_houses: [],
        old_houses: [],
        rent_houses: []
      },
      deletes: {
        projId: [],
        newId: [],
        oldId: [],
        rentId: []
      }
    }
  },
  created() {
    request({
      // 当vue实例被创建时获取楼盘信息并存入data的properties中
      url: "/house",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.all_data.properties = res.data;
        // this.propertiesBackup = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    request({
      // 当vue实例被创建时获取新房信息并存入data的new_houses中
      url: "/house/news",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.all_data.new_houses = res.data;
        // this.new_housesBackup = res.data
      })
      .catch((err) => {
        console.log(err);
      });
    request({
      // 当vue实例被创建时获取新房信息并存入data的new_houses中
      url: "/house/olds",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.all_data.old_houses = res.data;
        // this.old_housesBackup = res.data
      })
      .catch((err) => {
        console.log(err);
      });
    request({
      // 当vue实例被创建时获取新房信息并存入data的new_houses中
      url: "/house/rents",
      params: {
        addr: this.$store.state.addr,
      },
    })
      .then((res) => {
        this.all_data.rent_houses = res.data;
        // this.rent_housesBackup = res.data
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    deleteProjItem(item) {
      // console.log('proj test');
      // console.log(item);
      this.deletes.projId.push(item.id)
      let index = this.all_data.properties.indexOf(item)
      this.all_data.properties.splice(index, 1)
      // console.log(this.deletes.projId);
    },
    deleteNewItem(item) {
      console.log('new test');
      console.log(item);
      this.deletes.newId.push(item.hid)
      let index = this.all_data.new_houses.indexOf(item)
      this.all_data.new_houses.splice(index, 1)
      console.log(this.deletes.newId);
    },
    deleteOldItem(item) {
      // console.log('old test');
      // console.log(item);
      this.deletes.oldId.push(item.oid)
      let index = this.all_data.old_houses.indexOf(item)
      this.all_data.old_houses.splice(index, 1)
      // console.log(this.deletes.oldId);
    },
    deleteRentItem(item) {
      // console.log('rent test');
      // console.log(item);
      this.deletes.rentId.push(item.rid)
      let index = this.all_data.rent_houses.indexOf(item)
      this.all_data.rent_houses.splice(index, 1)
      // console.log(this.deletes.rentId);
    },
    projClick() {
      request({
        url: '/delete/proj',
        params: {
          projId: this.deletes.projId
        }
      })
      .then( () => {
        alert('删除成功')
      })
      .catch( err => {
        console.log(err);
      })
    },
    newClick() {
      request({
        url: '/delete/new',
        params: {
          newId: this.deletes.newId
        }
      })
      .then( () => {
        alert('删除成功')
      })
      .catch( err => {
        console.log(err);
      })
    },
    oldClick() {
      request({
        url: '/delete/old',
        params: {
          oldId: this.deletes.oldId
        }
      })
      .then( () => {
        alert('删除成功')
      })
      .catch( err => {
        console.log(err);
      })
    },
    rentClick() {
      request({
        url: '/delete/rent',
        params: {
          rentId: this.deletes.rentId
        }
      })
      .then( () => {
        alert('删除成功');
      })
      .catch( err => {
        console.log(err);
      })
    }
  }
};
</script>

<style scoped>
.active {
  background-color: rgb(150, 0, 0);
}
.tab-wrapper {
  width: 90%;
  background: linear-gradient(135deg,rgb(210,0,0),rgb(216, 163, 16));
  margin: 50px auto;
  overflow: hidden;
  border: 2px solid rgb(175, 0, 0);
  border-radius: 20px;
  box-shadow: 0 0 3px rgb(175, 0, 0),
      0 0 6px rgb(175, 0, 0);
  text-align: center;
  padding: 20px;
}
.tab-title {
  overflow: hidden;
  color: #fff;
  padding: 10px;
}
.tab-title li {
  float: left;
  width: 100px;
  text-align: center;
  cursor: pointer;
  padding: 10px 0;
}
.tab-content p {
  width: 45%;
  float: left;
  margin: 22px auto;
  margin-right: 5%;
}
.tab-content .decoration {
  display: inline-block;
  width: 20%;
  text-align: center;
}
.tab-content input {
  width: 80%;
  height: 30px;
  box-sizing: border-box;
  border: none;
  outline: none;
  border-radius: 3px;
}
.tab-content .btn {
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
.tab-content .btn:hover {
  background-color: rgb(175,0,0);
}
.tab-wrapper table {
  width: 100%;
  border: 1px solid #000;
  border-collapse: collapse;
  text-align: center;
}
.tab-wrapper table th, .tab-wrapper table td {
  border: 1px solid  #000;
  display: table-cell;
  vertical-align: middle;
  width: 90px;
}
.tab-wrapper table button {
  background-color: #fff;
  outline: 0;
}
</style>