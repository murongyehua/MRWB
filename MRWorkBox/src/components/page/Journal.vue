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
                <el-form :inline="true">
                <el-form-item label="处理时间">
                    <el-date-picker
                            v-model="query.dealDate"
                            type="daterange"
                            align="right"
                            unlink-panels
                            size="mini"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            :picker-options="pickerOptions">
                    </el-date-picker>
                </el-form-item>
                    <el-form-item label="处理人">
                        <el-input size="mini" v-model="query.dealUser"/>
                    </el-form-item>
                    <el-form-item label="标题关键字">
                        <el-input size="mini" v-model="query.dealUser"/>
                    </el-form-item>
                    <el-form-item label="分类">
                        <el-input size="mini" v-model="query.dealUser"/>
                    </el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                </el-form>
            </div>
            <el-button
                    type="success"
                    icon="el-icon-plus"
                    class="handle-del mr8"
                    @click="addJournal"
            >新增</el-button>
            <el-button
                    type="primary"
                    icon="el-icon-delete"
                    class="handle-del mr8"
                    @click="delAllSelection"
            >删除</el-button>
            <el-button
                    type="info"
                    icon="el-icon-s-grid"
                    class="handle-del mr10"
                    @click="changeView"
            >切换视图</el-button>
            <el-table
                :data="tableData"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change="handleSelectionChange"
                v-if="tableView"
            >
                <el-table-column v-for="item in tableFields" :prop="item.fieldName" :label="item.fieldName" align="left"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
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
                        >查看历史</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-row>
                <el-col :span="8">
                    <el-card class="box-card" v-if="!tableView">
                        <div slot="header" class="clearfix">
                            <span>卡片名称</span>
                            <el-button
                                    type="text"
                                    icon="el-icon-edit"
                                    style="float: right; padding: 3px 0"
                                    @click="handleEdit(scope.$index, scope.row)"
                            >编辑</el-button>
                            <el-button
                                    type="text"
                                    icon="el-icon-delete"
                                    class="red"
                                    style="float: right; padding: 3px 0"
                                    @click="handleDelete(scope.$index, scope.row)"
                            >查看历史</el-button>
                        </div>
                        <div v-for="o in 4" :key="o" class="text item">
                            {{'列表内容 ' + o }}
                        </div>
                    </el-card>
                </el-col>
            </el-row>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="渠道名称">
                    <el-input v-model="form.channelName"></el-input>
                </el-form-item>
                <el-form-item label="渠道编码">
                    <el-input v-model="form.channelCode"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 新增弹出框 -->
        <el-dialog title="新增" :visible.sync="addVisible" width="30%" center :close-on-click-modal="false" :destroy-on-close="true">
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
                <el-form-item v-for="item in tableFields" :label="item.fieldName">
                    <m-diy :type="item.fieldType" :content="item.fieldContent" :id="item.id" v-model="data.fieldData[item.sortnum]"></m-diy>
                </el-form-item>
                <el-form-item label="分类">
                    <el-select v-model="data.tag" value="" @visible-change="queryTags">
                        <el-option v-for="item in tags" :value="item.id" :label="item.tagname"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="saveAdd">确 定</el-button>
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
                dealDate: '', // 查询条件-处理时间
                dealUser: '', // 查询条件-处理人
                pageIndex: 1,
                pageSize: 10
            },
            data: {
                dealDate: '',
                dealUser: '',
                fieldData: [],
                tag: ''
            },
            tags: [], // 可选分类
            users: [], // 可选处理人
            tableData: [],
            tableView: true,
            tableFields: [],
            multipleSelection: [],
            delList: [],
            editVisible: false,
            addVisible: false,
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
    },
    methods: {
        // 获取 easy-mock 的模拟数据
        getData() {

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
        // 触发搜索按钮
        handleSearch() {
            this.$set(this.query, 'pageIndex', 1);
            this.getData();
        },
        // 删除操作
        handleDelete(index, row) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$message.success('删除成功');
                    this.tableData.splice(index, 1);
                })
                .catch(() => {});
        },
        // 多选操作
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        delAllSelection() {
            const length = this.multipleSelection.length;
            let str = '';
            this.delList = this.delList.concat(this.multipleSelection);
            for (let i = 0; i < length; i++) {
                str += this.multipleSelection[i].name + ' ';
            }
            this.$message.error(`删除了${str}`);
            this.multipleSelection = [];
        },
        changeView() {
            this.tableView = !this.tableView
        },
        // 编辑操作
        handleEdit(index, row) {
            this.idx = index;
            this.form = row;
            this.editVisible = true;
        },
        // 保存编辑
        saveEdit() {
            this.editVisible = false;
            this.$message.success(`修改第 ${this.idx + 1} 行成功`);
            this.$set(this.tableData, this.idx, this.form);
        },
        saveAdd() {
          console.info(this.data)
        },
        // 分页导航
        handlePageChange(val) {
            this.$set(this.query, 'pageIndex', val);
            this.getData();
        },
        getFields () {
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
</style>
