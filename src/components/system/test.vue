<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" @open="onOpen" @close="onClose" title="Dialog Titile">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="formData.userName" placeholder="请输入账号" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="formData.password" placeholder="请输入密码" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="权限" prop="type">
          <el-radio-group v-model="formData.type" size="medium">
            <el-radio-button v-for="(item, index) in typeOptions" :key="index" :label="item.value"
                             :disabled="item.disabled">{{item.label}}</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  inheritAttrs: false,
  components: {},
  props: [],
  data() {
    return {
      formData: {
        userName: undefined,
        password: undefined,
        type: undefined,
      },

    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    onOpen() {},
    onClose() {
      this.$refs['elForm'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handelConfirm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        this.close()
      })
    },
  }
}

</script>
<style>
</style>
