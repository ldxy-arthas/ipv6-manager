import { post, get } from './manager'

// 用户登录后获取token
export const userLogin = (username, password, data) => post(`/oauth2/token/${username}/${password}`, data)
// 通过token获取当前登录用户数据
export const tokenGetUser = (params) => get('/oauth2/getCurrentUser', params)

export const getUsersList = (userType, params) => get(`/users/getList/${userType}`, params)
// 编辑用户信息     /包括 学员 教师 机构
export const editUsers = (data) => post(`/users/update/`, data)

// 查询学生信息
export const getStudentInfo = (studentid, params) => get(`/student/username/${studentid}`, params)

// 更新学生信息
export const updateStudetnInfoByStudentId = (params) => post(`/student/update/`, params)

// 查询学校人数信息
export const getSchoolAcidInfo = () => get(`/acid/all/`)

// 查询学院人数信息
export const getNumOrgAcidInfo = () => get(`/acid/acid/org/`)

// 查询班级人数信息
export const getNumClassAcidInfo = () => get(`/acid/acid/class/`)

// 查询class人数信息
export const getOrgNum = () => get(`/acid/classNum/`)

// 查询org人数信息
export const getClassroomNameNum = () => get(`/acid/orgNum/`)

// 查询当前学院的所有班级的详细信息
export const getAllClassRoom = () => get(`/acid/getAllClassRoom/`)

// 查询当前班级的所有信息
export const getClassRoomInfo = (params) => post(`/acid/getClassRoomInfo/`, params)

// 获取班级的全部信息
export const classAllInfo = () => get("/acid/classAllInfo/")

// 获取班中做核算的详细数据
export const getClassRoomInfoNum = (params) => post(`/acid/getClassRoomInfoNum/`, params)

// 获取图标展示的数据
export const homeAllOrg = () => get(`/acid/homeAllOrg/`)

// 获取今天需要做的核酸床号
export const getBedNo = () => get(`/acid/note/`)