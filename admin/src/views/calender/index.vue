<template>
  <div>
    <el-calendar>
      <!-- 这里使用的是 2.5 slot 语法，对于新项目请使用 2.6 slot 语法-->
      <template
        slot="dateCell"
        slot-scope="{date, data}"
      >
        <div style="width:100%;height:100%" @click="() => handleClick(data)">
          <p :class="dateDict[data.day] ? 'is-selected' : ''" >
            {{ data.day.split('-').slice(1).join('-') }} {{ dateDict[data.day] ? '✔️'  : ''}}
          </p>
        </div>
      </template>
    </el-calendar>

    <el-row type="flex" justify="center">
      <el-button type="primary" @click="submitForm()">提交</el-button>
    </el-row>
  </div>
</template>

<style>
  .is-selected {
    color: #1989FA;
  }
</style>

<script>
  import { getList } from '@/api/calender'
  import { genCurrentMonth } from '@/utils/index'

export default {
  data() {
    return {
      dateDict: {}
    }
  },
  created() {

    this.fetchData()

    console.log(genCurrentMonth())
  },
  methods: {

    saveData() {
      // 向后端保存数据
    },

    submitForm() {
      console.log(this.dateDict);
      this.saveData();
    },

    handleClick(data){
      console.log('click')
      // {isSelected: true, type: "current-month", day: "2020-08-13"}
      console.log(data)

      if (data.isSelected) {
        const currentDay = data.day;

        console.log('currentDay==>',currentDay);
        if (this.dateDict.hasOwnProperty(currentDay)) {
          this.dateDict[currentDay] = !this.dateDict[currentDay]
        } else {
          this.dateDict[currentDay] = true
        }

        console.log('this.dateDict==>',this.dateDict)
      }



    },

    fetchData() {
      // 从后端拉取初始数据
      getList().then(response => {
        this.dateDict = response.data.item
      })
    }
  }
}
</script>
