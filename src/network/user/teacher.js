import {request} from "../request";

export function getAllTeacherHandle(pageNum, pageSize, str) {
    return request({
        method: "get",
        url: `/teacher/getAllTeacher/${pageNum}/${pageSize}`,
        params: {str}
    });
}

export function deleteTeacherHandle(sid) {
    return request({
        method: "post",
        url: "/teacher/deleteOne",
        data: {
            sid
        }
    });
}

export function updateTeacherHandle(teacher) {
    return request({
        method: "post",
        url: "/teacher/update",
        data: teacher
    });
}
