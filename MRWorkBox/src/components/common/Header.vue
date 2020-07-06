<template>
    <div class="header">
        <!-- 折叠按钮 -->
        <div class="collapse-btn" @click="collapseChage">
            <i v-if="!collapse" class="el-icon-s-fold"></i>
            <i v-else class="el-icon-s-unfold"></i>
        </div>
        <div class="logo">MR Work Box</div>
        <div class="header-right">
            <div class="header-user-con">
                <el-select v-model="checkedProject" @change="projectChange">
                    <el-option v-for="item in projects" :value="item.id" :label="item.name"></el-option>
                </el-select>
                <!-- 全屏显示 -->
                <div class="btn-fullscreen" @click="handleFullScreen">
                    <el-tooltip effect="dark" :content="fullscreen?`取消全屏`:`全屏`" placement="bottom">
                        <i class="el-icon-rank"></i>
                    </el-tooltip>
                </div>
                <!-- 消息中心 -->
                <!--<div class="btn-bell">-->
                <!--<el-tooltip-->
                <!--effect="dark"-->
                <!--:content="message?`有${message}条未读消息`:`消息中心`"-->
                <!--placement="bottom"-->
                <!--&gt;-->
                <!--<router-link to="/tabs">-->
                <!--<i class="el-icon-bell"></i>-->
                <!--</router-link>-->
                <!--</el-tooltip>-->
                <!--<span class="btn-bell-badge" v-if="message"></span>-->
                <!--</div>-->
                <!-- 用户头像 -->
                <div class="user-avator">
                    <img src="../../assets/img/img.jpg"/>
                </div>
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{username}}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item divided command="editUserInfo">修改资料</el-dropdown-item>
                        <el-dropdown-item divided command="loginout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <el-form ref="form" label-width="80px">
                <el-form-item label="昵称">
                    <el-input v-model="userInfo.nickname"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input type="password" v-model="userInfo.password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码">
                    <el-input type="password" v-model="userInfo.rePassword"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveUser">保 存</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
    import bus from '../common/bus';

    export default {
        data() {
            return {
                collapse: false,
                fullscreen: false,
                name: 'linxin',
                message: 2,
                projects: [],
                checkedProject: '',
                dialogVisible: false,
                userInfo: {
                    nickname: '',
                    password: '',
                    rePassword: ''
                }
            };
        },
        computed: {
            username() {
                let username = JSON.parse(localStorage.getItem('ms_username')).nickname;
                return username ? username : this.name;
            }
        },
        methods: {
            // 用户名下拉菜单选择事件
            handleCommand(command) {
                if (command == 'loginout') {
                    localStorage.removeItem('ms_username');
                    this.$router.push('/login');
                }
                if (command == 'editUserInfo') {
                    this.dialogVisible = true
                    let userInfo = JSON.parse(localStorage.getItem('ms_username'));
                    this.userInfo.nickname = userInfo.nickname
                }
            },
            // 关闭资料修改
            handleClose() {
                this.userInfo = {
                    nickname: '',
                    password: '',
                    rePassword: ''
                }
            },
            // 保存修改
            saveUser() {
                if (!this.userInfo.nickname || this.userInfo.nickname.length > 4) {
                    this.$message.warning('昵称长度为1-4位')
                    return
                }
                if (!this.userInfo.password || this.userInfo.password.length > 16 || this.userInfo.password.length < 6) {
                    this.$message.warning('密码长度应为6-16位')
                    return
                }
                if (this.userInfo.password !== this.userInfo.rePassword) {
                    this.$message.warning('两次输入密码不一致')
                    return
                }
                let data = {
                    id: JSON.parse(localStorage.getItem('ms_username')).id,
                    nickname: this.userInfo.nickname,
                    password: this.userInfo.password,
                    rePassword: this.userInfo.rePassword
                }
                this.API.editUser(data).then(res => {
                    if (res.code === '0') {
                        this.$alert('操作成功，请返回登录', '消息', {
                            confirmButtonText: '确定',
                            callback: action => {
                                localStorage.removeItem('ms_username');
                                this.$router.push('/login');
                            }
                        });
                    }
                })
                this.dialogVisible = false
            },
            // 侧边栏折叠
            collapseChage() {
                this.collapse = !this.collapse;
                bus.$emit('collapse', this.collapse);
            },
            // 全屏事件
            handleFullScreen() {
                let element = document.documentElement;
                if (this.fullscreen) {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                } else {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.msRequestFullscreen) {
                        // IE11
                        element.msRequestFullscreen();
                    }
                }
                this.fullscreen = !this.fullscreen;
            },
            // 获取项目列表
            getProject() {
                this.API.getProject().then(res => {
                    if (res.code === '0') {
                        this.projects = res.data
                        this.checkedProject = res.data[0]
                        bus.$emit('flushMenu', res.data[0].id)
                    } else {
                        let msg = res.info + ',请返回登录'
                        this.$confirm(msg, '提示', {
                            confirmButtonText: '确定',
                            type: 'warning'
                        }).then(() => {
                            this.$router.push('/login');
                        }).catch(() => {
                            this.$router.push('/login');
                        });
                    }
                })
            },
            projectChange() {
                bus.$emit('flushMenu', this.checkedProject.id)
            }
        },
        mounted() {
            if (document.body.clientWidth < 1500) {
                this.collapseChage();
            }
            this.getProject()
        }
    };
</script>
<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 100%;
        height: 70px;
        font-size: 22px;
        color: #fff;
    }

    .collapse-btn {
        float: left;
        padding: 0 21px;
        cursor: pointer;
        line-height: 70px;
    }

    .header .logo {
        float: left;
        width: 250px;
        line-height: 70px;
    }

    .header-right {
        float: right;
        padding-right: 50px;
    }

    .header-user-con {
        display: flex;
        height: 70px;
        align-items: center;
    }

    .btn-fullscreen {
        transform: rotate(45deg);
        margin-right: 5px;
        font-size: 24px;
    }

    .btn-bell,
    .btn-fullscreen {
        position: relative;
        width: 30px;
        height: 30px;
        text-align: center;
        border-radius: 15px;
        cursor: pointer;
    }

    .btn-bell-badge {
        position: absolute;
        right: 0;
        top: -2px;
        width: 8px;
        height: 8px;
        border-radius: 4px;
        background: #f56c6c;
        color: #fff;
    }

    .btn-bell .el-icon-bell {
        color: #fff;
    }

    .user-name {
        margin-left: 10px;
    }

    .user-avator {
        margin-left: 20px;
    }

    .user-avator img {
        display: block;
        width: 40px;
        height: 40px;
        border-radius: 50%;
    }

    .el-dropdown-link {
        color: #fff;
        cursor: pointer;
    }

    .el-dropdown-menu__item {
        text-align: center;
    }
</style>
