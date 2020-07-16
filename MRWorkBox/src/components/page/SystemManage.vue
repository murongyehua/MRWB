<template>
    <div>
    <div ref="tableWrap">
        <el-row :gutter="5">
            <el-col :span="12">
                <div>
                    <span>用户管理</span>
                    <el-button size="mini" @click="showAddUserModal">新增</el-button>
                    <el-button size="mini">分配权限</el-button>
                </div>
                <el-table
                        :data="userTableData"
                        border
                        class="table"
                        ref="userTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
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
                            :current-page="userSearchData.pageNum"
                            :page-size="userSearchData.pageSize"
                            :total="userTableTotal"
                            @current-change="handlePageChange"
                            small="true"
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
                userAdd: {
                    username: '',
                    password: '',
                    nickname: '',
                    userType: '0'
                }
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
            queryUser() {
                this.userLoading = true
                this.API.queryUserList().then(res => {
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
                this.API.queryModalList().then(res => {
                    if (res.code === '0') {
                        this.menuTableData = res.rows
                        this.menuTableTotal = res.total
                        this.menuLoading = false
                    }
                })
            },
            handlePageChange () {

            },
            handleSelectionChange () {

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
