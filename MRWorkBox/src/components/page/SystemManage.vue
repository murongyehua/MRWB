<template>
    <div>
    <div ref="tableWrap">
        <el-button size="mini" type="primary" @click="flushData">刷新数据</el-button>
        <el-row :gutter="5">
            <el-col :span="12">
                <div>
                    <span>用户管理</span>
                    <el-button size="mini" @click="showAddUserModal">新增</el-button>
                    <el-button size="mini" @click="distributeRight">分配权限</el-button>
                </div>
                <el-table
                        :data="userTableData"
                        border
                        class="table"
                        ref="userTable"
                        header-cell-class-name="table-header"
                        @selection-change="selection => selectChange(selection, 'user')"
                        @selection-all="selection => selectChange(selection, 'user')"
                        style="overflow: scroll"
                >
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column prop="index" label="ID" width="55" align="center"></el-table-column>
                    <el-table-column prop="nickname" label="昵称"></el-table-column>
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="userTypeText" label="类型"></el-table-column>
                    <el-table-column label="状态">
                        <template slot-scope="scope">
                            <el-tag
                                    :type="scope.row.userStateText==='正常'?'success':(scope.row.userStateText==='冻结'?'danger':'')"
                            >{{scope.row.userStateText}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button
                                    type="text"
                                    icon="el-icon-refresh-right"
                                    @click="resetPassword(scope.$index, scope.row)"
                            >重置密码</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="userSearchData.pageNum"
                            :page-size="userSearchData.pageSize"
                            :total="userTableTotal"
                            @current-change="handleUserPageChange"
                            :small="true"
                    ></el-pagination>
                </div>
            </el-col>
            <el-col :span="12">
                <div>
                    <span>项目管理</span>
                    <el-button size="mini">新增</el-button>
                    <el-button size="mini">开通模块</el-button>
                </div>
                <el-table
                        :data="projectTableData"
                        border
                        class="table"
                        ref="projectTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
                        style="overflow: scroll"
                >
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column prop="index" label="ID" width="55" align="center"></el-table-column>
                    <el-table-column prop="name" label="名称"></el-table-column>
                    <el-table-column label="状态">
                        <template slot-scope="scope">
                            <el-tag
                                    :type="scope.row.projectStateText==='正常'?'success':(scope.row.projectStateText==='冻结'?'danger':'')"
                            >{{scope.row.projectStateText}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTimeText" label="创建时间"></el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleEdit(scope.$index, scope.row)"
                            >编辑</el-button>
                            <el-button
                                    type="text"
                                    icon="el-icon-delete"
                                    class="red"
                                    @click="showHistory(scope.$index, scope.row)"
                            >删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="projectSearchData.pageNum"
                            :page-size="projectSearchData.pageSize"
                            :total="projectTableTotal"
                            @current-change="handlePageChange"
                    ></el-pagination>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="5">
            <el-col :span="12">
                <div>
                    <span>模块管理</span>
                    <el-button size="mini">新增</el-button>
                    <el-button size="mini">绑定菜单</el-button>
                </div>
                <el-table
                        :data="modalTableData"
                        border
                        class="table"
                        ref="modalTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
                        style="overflow: scroll"
                >
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column prop="index" label="ID" width="55" align="center"></el-table-column>
                    <el-table-column prop="name" label="名称"></el-table-column>
                    <el-table-column label="状态">
                        <template slot-scope="scope">
                            <el-tag
                                    :type="scope.row.modalStateText==='正常'?'success':(scope.row.modalStateText==='冻结'?'danger':'')"
                            >{{scope.row.modalStateText}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column prop="createTimeText" label="创建时间"></el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleEdit(scope.$index, scope.row)"
                            >编辑</el-button>
                            <el-button
                                    type="text"
                                    icon="el-icon-delete"
                                    class="red"
                                    @click="showHistory(scope.$index, scope.row)"
                            >删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="modalSearchData.pageNum"
                            :page-size="modalSearchData.pageSize"
                            :total="modalTableTotal"
                            @current-change="handlePageChange"
                    ></el-pagination>
                </div>
            </el-col>
            <el-col :span="12">
                <div>
                    <span>菜单管理</span>
                    <el-button size="mini">新增</el-button>
                </div>
                <el-table
                        :data="menuTableData"
                        border
                        class="table"
                        ref="menuTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
                        style="overflow: scroll"
                >
                    <el-table-column type="selection" width="55" align="center"></el-table-column>
                    <el-table-column prop="index" label="ID" width="55" align="center"></el-table-column>
                    <el-table-column prop="name" label="名称"></el-table-column>
                    <el-table-column label="操作" width="120" align="center">
                        <template slot-scope="scope">
                            <el-button
                                    type="text"
                                    icon="el-icon-edit"
                                    @click="handleEdit(scope.$index, scope.row)"
                            >编辑</el-button>
                            <el-button
                                    type="text"
                                    icon="el-icon-delete"
                                    class="red"
                                    @click="showHistory(scope.$index, scope.row)"
                            >删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pagination">
                    <el-pagination
                            background
                            layout="total, prev, pager, next"
                            :current-page="menuSearchData.pageNum"
                            :page-size="menuSearchData.pageSize"
                            :total="menuTableTotal"
                            @current-change="handlePageChange"
                    ></el-pagination>
                </div>
            </el-col>
        </el-row>
    </div>
        <el-dialog title="新增用户" :visible.sync="showUserAdd">
            <el-form :model="userAdd">
                <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input v-model="userAdd.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密码" :label-width="formLabelWidth">
                    <el-input v-model="userAdd.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="昵称" :label-width="formLabelWidth">
                    <el-input v-model="userAdd.nickname" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showUserAdd = false">取 消</el-button>
                <el-button type="primary" @click="addUser">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="分配权限" :visible.sync="showUserRightDistribute">
            <el-form :model="userRightDistribute">
                <el-form-item label="项目" :label-width="formLabelWidth">
                    <el-select v-model="userRightDistribute.projectId" value="" placeholder="请选择" @visible-change="projectChange">
                        <el-option v-for=" item in projectTableData" :value="item.id" :label="item.name"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="模块" :label-width="formLabelWidth">
                    <el-select v-model="userRightDistribute.modalIds" value="" multiple placeholder="请选择">
                        <el-option v-for="item in modalInProjects" :value="item.id" :label="item.name"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="showUserRightDistribute = false">取 消</el-button>
                <el-button type="primary" @click="addUserRight">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: 'systemManage',
        data() {
            return {
                formLabelWidth: '120px',
                userTableData: [],
                userTableTotal: 0,
                userLoading: true,
                userSearchData: {
                    pageNum: 1,
                    pageSize: 10,
                    sortName: '',
                    sortType: ''
                },
                userSelection: [],

                projectTableData: [],
                projectTableTotal: 0,
                projectLoading: true,
                projectSearchData: {
                    pageNum: 1,
                    pageSize: 20,
                    sortName: '',
                    sortType: ''
                },

                modalTableData: [],
                modalTableTotal: 0,
                modalLoading: true,
                modalSearchData: {
                    pageNum: 1,
                    pageSize: 20,
                    sortName: '',
                    sortType: ''
                },

                menuTableData: [],
                menuTableTotal: 0,
                menuLoading: true,
                menuSearchData: {
                    pageNum: 1,
                    pageSize: 20,
                    sortName: '',
                    sortType: ''
                },

                showUserAdd: false,
                showUserRightDistribute: false,
                userAdd: {
                    username: '',
                    password: '',
                    nickname: '',
                    userType: '0'
                },
                userRightDistribute: {
                    userId: '',
                    projectId: '',
                    modalIds: [],
                },
                modalInProjects: []
            }
        },
        mounted() {
            this.queryUser()
            this.queryProject()
            this.queryModal()
            this.queryMenu()
            let top = this.$refs.tableWrap.getBoundingClientRect().top;
            let windowHeight = document.documentElement.clientHeight;
            let finalHeight = windowHeight - top;
            this.$nextTick(() => {
                this.setTableHeight(finalHeight)
            });
            window.onresize = () => {
                this.setTableHeight(finalHeight)
            };
        },
        methods: {
            flushData() {
                this.queryUser()
                this.queryProject()
                this.queryModal()
                this.queryMenu()
            },
            queryUser() {
                this.userLoading = true
                let param = {
                    pageNum: this.userSearchData.pageNum,
                    pageSize: this.userSearchData.pageSize
                }
                this.API.queryUserList(param).then(res => {
                    if (res.code === '0') {
                        this.userTableData = res.rows
                        this.userTableTotal = res.total
                        this.userLoading = false
                    }
                })
            },
            queryProject() {
                this.projectLoading = true
                this.API.queryProjectList().then(res => {
                    if (res.code === '0') {
                        this.projectTableData = res.rows
                        this.projectTableTotal = res.total
                        this.projectLoading = false
                    }
                })
            },
            queryModal() {
                this.modalLoading = true
                this.API.queryModalList().then(res => {
                    if (res.code === '0') {
                        this.modalTableData = res.rows
                        this.modalTableTotal = res.total
                        this.modalLoading = false
                    }
                })
            },
            queryMenu() {
                this.menuLoading = true
                this.API.queryMenuList().then(res => {
                    if (res.code === '0') {
                        this.menuTableData = res.rows
                        this.menuTableTotal = res.total
                        this.menuLoading = false
                    }
                })
            },
            distributeRight () {
                if (this.userSelection.length !== 1) {
                    this.$message.warning('请选择一个用户进行操作')
                    return
                }
                this.userRightDistribute.userId = this.userSelection[0].id
                this.userRightDistribute.projectId = this.projectTableData.length > 0 ? this.projectTableData[0].id : ''
                this.showUserRightDistribute = !this.showUserRightDistribute
                this.projectChange()
            },
            projectChange () {
                this.userRightDistribute.modalIds = []
                let param = {
                    projectId: this.userRightDistribute.projectId,
                    userId: this.userRightDistribute.userId
                }
                this.API.getModalsByProject(param).then(res => {
                    if (res.code === '0') {
                        this.modalInProjects = res.data.modalInfos
                        this.userRightDistribute.modalIds = res.data.distributedModalIds
                    }
                })
            },
            addUserRight () {
                if (!this.userRightDistribute.projectId || this.userRightDistribute.projectId.length === 0) {
                    this.$message.warning('项目不能为空')
                    return
                }
                this.API.addUserRight(this.userRightDistribute).then(res => {
                    if (res.code === '0') {
                        this.$message.success(res.info)
                        this.userRightDistribute = {
                            projectId: '',
                            modalIds: '',
                            userId: ''
                        }
                        this.showUserRightDistribute = !this.showUserRightDistribute
                    }
                })
            },
            resetPassword (index, row) {
                this.$confirm('确认要重置用户【' + row.nickname + '】的密码？', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.API.resetPassword({userId: row.id}).then(res => {
                        if (res.code === '0') {
                            this.$message.success(res.info)
                        }
                    })
                })
            },
            handleUserPageChange(val) {
                this.handlePageChange(val, 'user')
            },
            handlePageChange (num, type) {
                this.$set(this[`${type}SearchData`], 'pageNum', num);
                this.queryUser();
            },
            handleSelectionChange () {

            },
            selectChange(selection, type) {
                this[`${type}Selection`] = selection
            },
            setTableHeight (finalHeight) {
                finalHeight = finalHeight / 2 - 65;
                this.$refs.userTable.height = `${finalHeight}px`;
                this.$refs.projectTable.height = `${finalHeight}px`;
                this.$refs.modalTable.height = `${finalHeight}px`;
                this.$refs.menuTable.height = `${finalHeight}px`;
            },
            showAddUserModal () {
                this.showUserAdd = true
            },
            addUser () {
                this.showUserAdd = false
                this.API.addUser(this.userAdd).then(res =>{
                    if (res.code === '0') {
                        this.$message.info(res.info)
                        this.queryUser()
                    }
                })
                this.userAdd = {
                        username: '',
                        password: '',
                        nickname: '',
                        userType: '0'
                }
            }
        }
    }

</script>
<style rel="stylesheet/scss" lang="scss">
</style>
