<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 值班记录
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-drawer
                        :title="detail.title"
                        :visible.sync="detailDrawer">
                    <el-row>
                        <el-col :span="12">
                            <span><b>处理人: </b></span>{{detail.dealUserText}}
                        </el-col>
                        <el-col :span="12">
                            <span><b>处理时间: </b></span>{{detail.dealDate}}
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <span><b>创建人: </b></span>{{detail.createUserText}}
                        </el-col>
                        <el-col :span="12">
                            <span><b>创建时间: </b></span>{{detail.createTimeText}}
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <span><b>最后修改时间: </b></span>{{detail.lastModifyTimeText}}
                        </el-col>
                        <el-col :span="12">
                            <span><b>最后修改人: </b></span>{{detail.lastModifyUserText}}
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <span><b>分类: </b></span>{{detail.tagText}}
                        </el-col>
                    </el-row>
                    <br>
                    <hr>
                    <br>
                    <el-row v-for="item in tableFields" class="card">
                        <span><b>{{item.fieldName + ': '}}</b></span>{{detail[item.fieldName]}}
                    </el-row>
                </el-drawer>
                <el-drawer
                        title="历史版本"
                        :visible.sync="historyDrawer"
                        :with-header="false"
                        direction="ttb">
                    <el-table
                            :data="historyTableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column label="标题" prop="title" show-overflow-tooltip></el-table-column>
                        <el-table-column v-for="item in tableFields" :prop="item.id" :label="item.fieldName"
                                         align="left" show-overflow-tooltip></el-table-column>
                        <el-table-column label="处理时间" prop="dealDate" show-overflow-tooltip></el-table-column>
                        <el-table-column label="处理人" prop="dealUserText" show-overflow-tooltip></el-table-column>
                        <el-table-column label="修改时间" prop="lastModifyTimeText" show-overflow-tooltip></el-table-column>
                        <el-table-column label="修改人" prop="lastModifyUserText" show-overflow-tooltip></el-table-column>
                    </el-table>
                </el-drawer>
                <el-form :inline="true">
                    <el-form-item label="处理时间">
                        <el-date-picker
                                v-model="query.dealDate"
                                type="daterange"
                                align="right"
                                unlink-panels
                                size="mini"
                                :clearable="false"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期"
                                :picker-options="pickerOptions">
                        </el-date-picker>
                    </el-form-item>
                    <el-form-item label="处理人">
                        <el-select size="mini" v-model="query.dealUser" value="">
                            <el-option v-for="item in users" :value="item.id" :label="item.nickname"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="标题关键字">
                        <el-input size="mini" v-model="query.titleLike"/>
                    </el-form-item>
                    <el-form-item label="分类">
                        <el-select size="mini" v-model="query.tag" value="">
                            <el-option v-for="item in tags" :value="item.id" :label="item.tagname"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                </el-form>
            </div>
            <el-button
                    type="success"
                    icon="el-icon-plus"
                    class="handle-del mr8"
                    @click="addJournal"
            >新增
            </el-button>
            <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr8"
                    @click="delAllSelection"
                    v-show="tableView"
            >删除
            </el-button>
            <el-button
                    type="info"
                    icon="el-icon-view"
                    class="handle-del mr10"
                    @click="changeView"
            >切换视图
            </el-button>
            <el-popover
                    width="400"
                    trigger="click"
                    style="float: right">
                <el-button size="mini" style="margin-bottom: 5px;" @click="addTag">新增</el-button>
                <el-table :data="tags">
                    <el-table-column width="150" property="tagname" label="名称"></el-table-column>
                    <el-table-column width="250" property="" label="操作">暂不支持</el-table-column>
                </el-table>
                <el-button
                        slot="reference"
                        type="info"
                        icon="el-icon-s-grid"
                        class="handle-del mr8"
                        @click="queryTags"
                >分类管理
                </el-button>
            </el-popover>
            <el-table
                    :data="tableData"
                    border
                    class="table"
                    ref="multipleTable"
                    header-cell-class-name="table-header"
                    @selection-change="handleSelectionChange"
                    @row-dblclick="dbClick"
                    v-if="tableView"
            >
                <el-table-column type="selection"></el-table-column>
                <el-table-column label="标题" prop="title" show-overflow-tooltip></el-table-column>
                <el-table-column v-for="item in tableFields" :prop="item.id" :label="item.fieldName"
                                 align="left" show-overflow-tooltip></el-table-column>
                <el-table-column label="处理时间" prop="dealDate" show-overflow-tooltip></el-table-column>
                <el-table-column label="处理人" prop="dealUserText" show-overflow-tooltip></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button
                                type="text"
                                icon="el-icon-edit"
                                @click="handleEdit(scope.$index, scope.row)"
                        >编辑
                        </el-button>
                        <el-button
                                type="text"
                                icon="eel-icon-notebook-2"
                                @click="showHistory(scope.$index, scope.row)"
                        >查看历史
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-row>
                <el-card class="box-card" v-if="!tableView" v-for="item in tableData" :key="item.id">
                    <div slot="header" class="clearfix">
                        <span class="card">{{item.title}}</span>
                        <el-button
                                type="text"
                                icon="el-icon-edit"
                                style="float: right; padding: 3px 0"
                                @click="handleEdit(scope.$index, scope.row)"
                        >编辑
                        </el-button>
                        <el-button
                                type="text"
                                icon="el-icon-notebook-2"
                                style="float: right; padding: 3px 0"
                                @click="showHistory(scope.$index, scope.row)"
                        >查看历史
                        </el-button>
                    </div>
                    <div v-for="o in tableFields" :key="o.id" class="text item card">
                        <b>{{o.fieldName + ': '}}</b>{{item[o.id]}}
                    </div>
                    <div class="text item card"><b>处理时间：</b>{{item.title}}</div>
                    <div class="text item card"><b>处理人：</b>{{item.dealUserText}}</div>
                </el-card>
            </el-row>
            <div class="pagination">
                <el-pagination
                        background
                        layout="total, prev, pager, next"
                        :current-page="query.pageNum"
                        :page-size="query.pageSize"
                        :total="pageTotal"
                        @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑记录弹出框 -->
        <el-dialog title="编辑记录" :visible.sync="editVisible" width="30%" center :close-on-click-modal="false"
                   :destroy-on-close="true" @close="resetData">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="处理时间">
                    <el-date-picker
                            v-model="data.dealDate"
                            type="date"
                            placeholder="选择处理时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="处理人">
                    <el-select v-model="data.dealUser" value="" @visible-change="queryUsers">
                        <el-option v-for="item in users" :value="item.id" :label="item.nickname"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标题">
                    <el-input v-model="data.title"></el-input>
                </el-form-item>
                <el-form-item v-for="item in tableFields" :label="item.fieldName">
                    <m-diy :type="item.fieldType" :content="item.fieldContent" :id="item.id"
                           v-model="data.fieldData[item.sortnum]" :value="data.fieldData[item.sortnum]"></m-diy>
                </el-form-item>
                <el-form-item label="分类">
                    <el-select v-model="data.tag" value="" @visible-change="queryTags">
                        <el-option v-for="item in tags" :value="item.id" :label="item.tagname"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="save('edit')">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 新增记录弹出框 -->
        <el-dialog title="新增记录" :visible.sync="addVisible" width="30%" center :close-on-click-modal="false"
                   :destroy-on-close="true" @close="resetData">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="处理时间">
                    <el-date-picker
                            v-model="data.dealDate"
                            type="date"
                            placeholder="选择处理时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="处理人">
                    <el-select v-model="data.dealUser" value="" @visible-change="queryUsers">
                        <el-option v-for="item in users" :value="item.id" :label="item.nickname"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="标题">
                    <el-input v-model="data.title"></el-input>
                </el-form-item>
                <el-form-item v-for="item in tableFields" :label="item.fieldName">
                    <m-diy :type="item.fieldType" :content="item.fieldContent" :id="item.id"
                           v-model="data.fieldData[item.sortnum]" :value="data.fieldData[item.sortnum]"></m-diy>
                </el-form-item>
                <el-form-item label="分类">
                    <el-select v-model="data.tag" value="" @visible-change="queryTags">
                        <el-option v-for="item in tags" :value="item.id" :label="item.tagname"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="save('add')">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 新增分类 -->
        <el-dialog title="新增分类" :visible.sync="addTagVisible" width="30%" :close-on-click-modal="false"
                   :destroy-on-close="true">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="分类名称">
                    <el-input v-model="tagData.tagName"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveTag">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    import MDiy from "../../assembly/m-diy/m-diy";

    export default {
        name: 'basetable',
        components: {MDiy},
        data() {
            return {
                query: {
                    dealDate: [new Date(), new Date()], // 查询条件-处理时间
                    dealUser: '', // 查询条件-处理人
                    titleLike: '',
                    tag: '',
                    pageNum: 1,
                    pageSize: 10,
                    sortName: 'last_modify_time',
                    sortType: 'desc'
                },
                data: {
                    dealDate: '',
                    dealUser: '',
                    fieldData: [],
                    tag: '',
                    title: ''
                },
                detail: {},
                tagData: {
                    tagName: ''
                },
                tags: [], // 可选分类
                users: [], // 可选处理人
                tableData: [],
                historyTableData: [],
                tableView: true,
                tableFields: [],
                multipleSelection: [],
                delList: [],
                editVisible: false,
                addVisible: false,
                addTagVisible: false,
                detailDrawer: false,
                historyDrawer: false,
                pageTotal: 0,
                form: {},
                idx: -1,
                id: -1,
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            };
        },
        created() {
            this.getData();
        },
        mounted() {
            this.getFields();
            this.queryTags();
            this.queryUsers();
        },
        methods: {
            // 获取表格数据
            getData() {
                let param = {
                    dealDateStart: this.global.dateFormat('yyyy-MM-dd', this.query.dealDate[0]),
                    dealDateEnd: this.global.dateFormat('yyyy-MM-dd', this.query.dealDate[1]),
                    dealUser: this.query.dealUser,
                    tag: this.query.tag,
                    titleLike: this.query.titleLike,
                    pageSize: this.query.pageSize,
                    pageNum: this.query.pageNum,
                    sortName: this.query.sortName,
                    sortType: this.query.sortType
                }
                this.API.queryJournalSummary(param).then(res => {
                    if (res.code === '0') {
                        this.pageTotal = res.total
                        this.tableData = res.rows
                        for (let i = 0; i < this.tableData.length; i++) {
                            for (let j = 0; j < this.tableData[i].contents.length; j++) {
                                this.$set(this.tableData[i], this.tableData[i].contents[j].fieldId, this.tableData[i].contents[j].content)
                            }
                        }
                    }
                })
            },
            // 获取单条记录
            getById() {
                this.API.getJournalSummaryById({summaryId: this.form.id}).then(res => {
                    if (res.code === '0') {
                        let contents = []
                        res.data.contents.forEach(x => {
                            contents[x.sortNum] = x.content
                        })
                        this.data = {
                            dealDate: res.data.dealDate,
                            dealUser: res.data.dealUser,
                            fieldData: contents,
                            tag: res.data.tagId,
                            title: res.data.title
                        }
                    } else {
                        this.$message.error(res.info)
                    }
                })
            },
            // 获取分类
            queryTags() {
                this.API.queryJournalTag().then(res => {
                    if (res.code === '0') {
                        this.tags = res.rows
                    }
                })
            },
            // 获取用户
            queryUsers() {
                this.API.getUsers().then(res => {
                    if (res.code === '0') {
                        this.users = res.data
                    }
                })
            },
            // 显示新增记录的弹框
            addJournal() {
                this.addVisible = true
            },
            addTag() {
                this.addTagVisible = true
            },
            // 触发搜索按钮
            handleSearch() {
                this.$set(this.query, 'pageNum', 1);
                this.getData();
            },
            // 查看历史
            showHistory(index, row) {
                this.API.queryJournalSummaryHistory({summaryId: row.id}).then(res => {
                    if (res.code === '0') {
                        this.historyDrawer = true
                        this.historyTableData = res.rows
                        for (let i = 0; i < this.historyTableData.length; i++) {
                            for (let j = 0; j < this.historyTableData[i].contents.length; j++) {
                                this.$set(this.historyTableData[i], this.historyTableData[i].contents[j].fieldId, this.historyTableData[i].contents[j].content)
                            }
                        }
                    }
                })
            },
            // 多选操作
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            dbClick(val) {
                this.detail = val
                this.detailDrawer = true
            },
            delAllSelection() {
                if (this.multipleSelection.length === 0) {
                    this.$message.warning('请选择所要删除的记录')
                    return
                }
                this.$confirm('确定要删除吗？', '提示', {
                    type: 'warning'
                })
                    .then(() => {
                        let ids = []
                        for (let i = 0; i < this.multipleSelection.length; i++) {
                            ids.push(this.multipleSelection[i].id)
                        }
                        let data = {
                            ids: ids
                        }
                        this.API.deleteJournalSummary(data).then(res => {
                            if (res.code === '0') {
                                this.$message.success(res.info)
                                this.getData()
                            } else {
                                this.$message.error(res.info)
                            }
                        })
                    })
                    .catch(() => {
                    });
            },
            changeView() {
                this.tableView = !this.tableView
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.getById()
                this.editVisible = true;
            },
            // 保存记录
            save(type) {
                if (this.data.fieldData.length < this.tableFields.length) {
                    this.$message.warning('存在必填的未填项')
                    return
                }
                for (let i = 0; i < this.data.fieldData.length; i++) {
                    if (!this.data.fieldData[i] || this.data.fieldData[i].length === 0) {
                        this.$message.warning('存在必填的未填项')
                        return
                    }
                }
                if (!this.data.dealDate || this.data.dealDate.length === 0) {
                    this.$message.warning('处理时间不能为空')
                    return
                }
                if (!this.data.dealUser || this.data.dealUser.length === 0) {
                    this.$message.warning('处理人不能为空')
                    return
                }
                if (!this.data.title || this.data.title.length === 0) {
                    this.$message.warning('标题不能为空')
                    return
                }
                if (!this.data.tag || this.data.dealUser.tag === 0) {
                    this.$message.warning('分类不能为空')
                    return
                }
                let fieldContents = this.data.fieldData
                for (let i = 0; i < this.data.fieldData.length; i++) {
                    if (Array.isArray(this.data.fieldData[i])) {
                        fieldContents[i] = this.data.fieldData[i].join('`||@')
                    }
                }
                let data = {
                    dealUser: this.data.dealUser,
                    dealDate: this.global.dateFormat('yyyy-MM-dd', this.data.dealDate),
                    tagId: this.data.tag,
                    title: this.data.title,
                    fieldContents: fieldContents
                }
                let method
                if (type === 'add') {
                    method = this.API.addJournalSummary
                } else {
                    method = this.API.editJournalSummary
                    data.id = this.form.id
                }
                method(data).then(res => {
                    if (res.code === '0') {
                        this.$message.success(res.info)
                        this.addVisible = false
                        this.editVisible = false
                        this.resetData()
                        this.getData()
                    }
                })
            },
            resetData() {
                this.data = {
                    dealDate: '',
                    dealUser: '',
                    fieldData: [],
                    tag: '',
                    title: ''
                }
            },
            saveTag() {
                if (!this.tagData.tagName || this.tagData.tagName.length === 0) {
                    this.$message.warning('请填写分类名称')
                    return
                }
                this.API.addJournalTag({name: this.tagData.tagName}).then(res => {
                    if (res.code === '0') {
                        this.$message.success(res.info)
                        this.addTagVisible = false
                    } else {
                        this.$message.error(res.info)
                    }
                })
            },
            // 分页导航
            handlePageChange(val) {
                this.$set(this.query, 'pageNum', val);
                this.getData();
            },
            getFields() {
                this.API.getJournalFields().then(res => {
                    this.tableFields = res.data
                })
            }
        }
    };
</script>

<style scoped>
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }

    .table {
        width: 100%;
        font-size: 14px;
    }

    .red {
        color: #ff0000;
    }

    .mr10 {
        margin-right: 10px;
    }

    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }

    .card {
        word-wrap: break-word
    }
</style>
