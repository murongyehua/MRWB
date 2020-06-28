<template>
    <div>
    <div ref="tableWrap">
        <el-row :gutter="5">
            <el-col :span="12">
                <el-table
                        :data="userTableData"
                        border
                        class="table"
                        ref="userTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
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
                                    @click="handleDelete(scope.$index, scope.row)"
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
                    ></el-pagination>
                </div>
            </el-col>
            <el-col :span="12">
                <el-table
                        :data="projectTableData"
                        border
                        class="table"
                        ref="projectTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
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
                                    @click="handleDelete(scope.$index, scope.row)"
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
                <el-table
                        :data="modalTableData"
                        border
                        class="table"
                        ref="modalTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
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
                                    @click="handleDelete(scope.$index, scope.row)"
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
                <el-table
                        :data="menuTableData"
                        border
                        class="table"
                        ref="menuTable"
                        header-cell-class-name="table-header"
                        @selection-change="handleSelectionChange"
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
                                    @click="handleDelete(scope.$index, scope.row)"
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
    </div>
</template>
<script>
    export default {
        name: 'systemManage',
        data() {
            return {
                userTableData: [],
                userTableTotal: 0,
                userLoading: true,
                userSearchData: {
                    pageNum: 1,
                    pageSize: 20,
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
        }
    }

</script>
<style rel="stylesheet/scss" lang="scss">
</style>
