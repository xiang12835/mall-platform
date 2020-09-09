<template>
  <el-upload
    ref="upload"
    multiple
    action="/"
    :limit="5"
    :on-exceed="handleExceed"
    :show-file-list="false"
    :auto-upload="true"
    :http-request='addFileToFormData'
  >
    <el-button slot="trigger" type="success" @click="upload">上传</el-button>
  </el-upload>
</template>

<script>
  export default {
    name: 'index',
    data() {
      return {
        fileFormData: null, // 将要上传的formdata数据
        percentage: 0, // 存放上传百分比
      }
    },

    created () {
      this.fileFormData = new FormData();
    },

    methods: {

      // 选取文件超过数量提示
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 5 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },

      addFileToFormData(file) {
        /*
           * 每选一个文件都会执行一次该函数
           * 也就把每个文件都添加到 this.fileFormData 里面
           * 在添加之前也可以加上自己的一些判断逻辑
           * 当然也可以直接利用 el-upload 组件自带的限制功能
          */
        this.fileFormData.append('file[]', file.file);

        console.log(this.fileFormData.getAll('file[]'));

      },

      upload() {

        const formData = this.fileFormData;
        const fn = this.uploadProgress; // 我们自己处理上传进度的函数

        axios({
          method: 'post',
          url: '/yourURL',
          headers: {
            'Content-Type': 'multipart/form-data'
          },
          data: formData,
          onUploadProgress: fn, // `onUploadProgress` 允许为上传处理进度事件
        });
      },

      uploadProgress(progressEvent){
        /*
           * progressEvent.loaded :已上传量
           * progressEvent.total :上传的总大小
          */
        const p = Math.floor((progressEvent.loaded / progressEvent.total) * 100);
        this.percentage = p;
      },



    }





  }


</script>

<style scoped>

</style>
