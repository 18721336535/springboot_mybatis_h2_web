var vue = new Vue({
    el: "#app",
    data: {
        file: {id:"",fileName:"aaa",filePath:"",fileSize:"",lastUpdateTime:"",status:"",columns:[]},
        fileList: [],
        fileColumns: []
    },
    methods: {
        findAll: function () {
            var _this = this;
            axios.get("/file/findAll").then(function (response) {
                _this.fileList = response.data;
                console.log(_this.fileList);
            }).catch(function (err) {
                console.log(err);
            });
        },
        findById: function (fileid) {
            var _this = this;
            axios.get("/file/findById", {
                params: {
                    id: fileid
                }
            }).then(function (response) {
              _this.file = response.data;
                $('#myModal').modal("show");
                $(document).ready(function () {
                var input_checkbox_content=document.getElementById("file-column-input-checkbox");
                var ii="x";
                var inputs="<div>check items:<br>";
                inputs+="<input type='checkbox' name='labelname' value='"+01+"' checked>"+empty+"</input>";
                inputs+="<input type='checkbox' name='labelname' value='"+02+"' checked>"+trailer+"</input>";
                inputs+="<input type='checkbox' name='labelname' value='"+03+"' checked>"+sum+"</input>";
                inputs+="<input type='checkbox' name='labelname' value='"+04+"' checked>"+duplicate_column+"</input>";
                inputs+="</div>";
                inputs+="<div>fileds to check:<br>:";
                for (var i = 1; i <= _this.file.columns.length; i++) {
                    inputs+="<input type='checkbox' name='labelname' value='"+i+"' checked>"+i+"."+_this.file.columns[i-1]+"</input>";
                    if ((i+1)%10 == 0) inputs+="<br>";
                }
                inputs+="</div>";
                input_checkbox_content.innerHTML=inputs;
                });
            }).catch(function (err) {
            });

        },
        update: function (file) {
            var _this = this;
            axios.post("/file/update",_this.file).then(function (response) {
                _this.findAll();
            }).catch(function (err) {
            });
        }

    },

    created(){
        this.findAll();
    }
});