import {request} from "../request";

// 定义新的文件进行引用,单独管理文件
export function loginHandle(ruleForm) {
    return request({
        method: "post",
        url: "/employment/login",
        // post: 在此处是一个对象 ruleForm
        data: ruleForm,
    });
}

export function getAllStudentHandle(pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/user/getAllUser/${pageNum}/${pageSize}`,
        params: {str}
    });
}

export function updateUserHandle(user) {
    return request({
        method: "post",
        url: "/user/update",
        data: user
    });
}

export function AddUserHandle(user) {
    return request({
        method: "post",
        url: "/user/saveUser",
        data: user
    });
}

export function deleteUserHandle(user) {
    return request({
        method: "post",
        url: "/user/delete",
        data: user
    });
}

export function delAllIpserver(arr) {
    return request({
        method: "post",
        url: "/user/deleteAll",
        data: arr
    });
}

export function updatePassword(oldPassword, newPassword) {
    return request({
        method: "post",
        url: "/user/updatePassword",
        params: {
            oldPassword,
            newPassword
        }
    });
}
export function getOneStudent() {
    return request({
        method: "get",
        url: "/student/getOne",
    });
}
export function updateStudent(student) {
    return request({
        method: "post",
        url: "/student/update",
        data: student
    });
}
