<template>
    <div>
        <el-input v-model="data" v-show="type === '1'" @blur="changeValue"></el-input>
        <el-radio v-model="data" v-show="type === '2'" v-for="item in finalContent" :label="item" :value="item" @change="changeValue"></el-radio>
        <el-checkbox-group v-model="arrayData" v-show="type === '3'" @change="changeArrayValue">
            <el-checkbox v-for="item in finalContent" :label="item" :value="item"></el-checkbox>
        </el-checkbox-group>
        <el-input type="textarea" v-model="data" v-show="type === '4'" @blur="changeValue"></el-input>
    </div>
</template>

<script>
    export default {
        name: "m-diy",
        model: {
            prop: 'value',
            event: 'cc'
        },
        props: {
            value: '',
            type: {
                type: String,
                default: ''
            },
            content: {
                type: String,
                default: ''
            },
            id:{
                type: String,
                default: ''
            }
        },
        data() {
            return {
                data: '',
                arrayData: [],
                finalContent: []
            }
        },
        mounted() {
            if (this.content) {
                this.finalContent = this.content.split(',')
            }
        },
        methods: {
            changeValue() {
                this.$emit('cc',this.data)
            },
            changeArrayValue() {
                this.$emit('cc',this.arrayData)
            }
        }
    }
</script>

<style scoped>

</style>