import { request } from "../request";

// 定义新的文件进行引用,单独管理文件
export function loginHandle(ruleForm) {
    return request({
        method: "post",
        url: "/employment/login",
        // post: 在此处是一个对象 ruleForm
        data: ruleForm,
    });
}
