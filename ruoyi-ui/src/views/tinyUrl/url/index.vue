<template>
  <div class="app-container">
    

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['tinyUrl:url:add']"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tinyUrlList">
      <el-table-column width="55" align="center" />
      <el-table-column label="序号" align="center" prop="tinyUrlId" width="100" />
      <el-table-column
        label="新链接"
        align="center"
        prop="url"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="过期时间" align="center" prop="expireTime" width="100">
      
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" width="100" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="100">
    
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:notice:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="原链接" prop="originalUrl">
              <el-input  type="textarea" v-model="form.originalUrl" placeholder="请输入原链接" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
           <el-form-item label="到期时间" prop="expireDate">
              <el-input v-model.number="form.expireDate" placeholder="请输入链接到期时间" />
            </el-form-item>
          </el-col>
          <el-col :span="24" style="text-align:center">
                   <el-button  type="primary" @click="submitForm">生 成</el-button>
          </el-col>
          <el-col :span="24">
           <el-form-item label="短链接" prop="url">
             <span @click="linkByUrl">{{form.url}}</span>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer" style="padding-top:30px">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createTinyUrl,delTinyUrl,listTinyUrl,viewUrl } from "@/api/tinyUrl/url";

export default {
  name: "TinyUrl",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表格数据
      tinyUrlList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createBy: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        expireTime: [
          { required: true,type: 'number', message: "请输入过期时长", trigger: "blur" }
        ],
        originalUrl: [
          { required: true, message: "原链接不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      listTinyUrl(this.queryParams).then(response => {
        this.tinyUrlList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        tinyUrlId: undefined,
        originalUrl: undefined,
        expireDate: undefined,
        url: undefined,
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加链接";
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            createTinyUrl(this.form).then(response => {
              if (response.code === 200) {
                this.form.url = response.data.url
                this.msgSuccess("新增成功");
                //this.open = false;
                this.getList();
              }
            });
        }
      });
    },
    linkByUrl(){
        window.open(this.form.originalUrl,"_blank");                 


    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const tinyUrlId = row.tinyUrlId;
      this.$confirm('是否确认删除编号为"' + tinyUrlId + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTinyUrl(tinyUrlId);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    }
  }
};
</script>
<style lang="scss" >
 .el-textarea__inner{
   height: 150px;
 }
</style>