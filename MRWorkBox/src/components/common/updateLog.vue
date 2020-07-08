<template>
    <div style="margin-left: 30%; width: 40%; margin-top: 100px">
        <div class="block">
            <el-timeline>
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
    </div>
</template>

<script>
    export default {
        name: "updateLog"
    }
</script>

<style scoped>

</style>