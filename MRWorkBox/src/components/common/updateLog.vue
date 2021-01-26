<template>
    <div style="margin-left: 30%; width: 40%; margin-top: 100px; height: 750px; overflow: auto">
        <el-timeline>
            <el-timeline-item timestamp="2021/1/26" placement="top" color="#0bbd87">
                <el-card>
                    <h4>版本1.1.0发布</h4>
                    <p>新增：</p>
                    <p>1.管理员操作界面化，可在页面上对用户、项目、模块、菜单进行基本管理</p>
                    <p>2.值班日志支持导出功能，目前最多支持一次导出1w条数据</p>
                    <p>优化：</p>
                    <p>1.值班日志默认查询时间改为当前周</p>
                </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2020/7/16" placement="top" color="#0bbd87">
                <el-card>
                    <h4>版本1.0.3发布</h4>
                    <p>优化：</p>
                    <p>1.值班日志页面所有下拉框支持模糊搜索</p>
                    <p>新增：</p>
                    <p>1.分类名称可以进行修改了</p>
                </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2020/7/13" placement="top" color="#0bbd87">
                <el-card>
                    <h4>版本1.0.2发布</h4>
                    <p>修复：</p>
                    <p>1.值班日志列表切换视图后处理时间显示错误的问题</p>
                    <p>2.修改资料弹出框关闭按钮点击后不生效的问题</p>
                    <p>3.其他已知问题</p>
                </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2020/7/8" placement="top" color="#0bbd87">
                <el-card>
                    <h4>版本1.0.1发布</h4>
                    <p>修复：</p>
                    <p>1.值班日志查看详情时内容超长后无法下拉的问题</p>
                    <p>2.登录超时后弹出多个【未登录】消息的问题</p>
                    <p>优化：</p>
                    <p>1.值班日志查询条件增加清除按钮</p>
                    <p>2.值班日志新增时处理时间默认当天、处理人默认当前用户</p>
                    <p>3.值班日志列表页显示分类</p>
                    <p>新增：</p>
                    <p>1.可查看系统更新日志</p>
                </el-card>
            </el-timeline-item>
            <el-timeline-item timestamp="2020/7/6" placement="top" color="#0bbd87">
                <el-card>
                    <h4>版本1.0.0发布</h4>
                    <p>MR 工作盒子投入使用</p>
                    <p>值班日志模块基本功能可用</p>
                </el-card>
            </el-timeline-item>
        </el-timeline>
    </div>
</template>

<script>
    export default {
        name: "updateLog"
    }
</script>

<style scoped>

</style>