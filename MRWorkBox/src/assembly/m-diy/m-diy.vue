<template>
    <div>
        <el-input v-model="inputData" v-show="type === '1'"></el-input>
    </div>
</template>

<script>
    export default {
        name: "m-diy",
        props: {
            type: {
                type: String,
                default: ''
            },
            content: {
                type: String,
                default: ''
            }
        },
        data() {
            return {
                data: '',
                inputData: '',
                selectData: [],
                radioData: '',
                checksData: [],
                textData: ''
            }
        }
    }
</script>

<style scoped>

</style>