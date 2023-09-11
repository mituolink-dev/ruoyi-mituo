<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="是否续订" prop="autoRenewing">-->
<!--        <el-input-->
<!--          v-model="queryParams.autoRenewing"-->
<!--          placeholder="请输入是否续订"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅" prop="cancelReason">-->
<!--        <el-input-->
<!--          v-model="queryParams.cancelReason"-->
<!--          placeholder="请输入0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="授予订阅时间" prop="startTime" width="180">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.startTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择授予订阅时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订阅到期时间" prop="expireTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.expireTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择订阅到期时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="自动续订时间" prop="autoResumeTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.autoResumeTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择自动续订时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="订阅价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入订阅价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="订阅的付款状态" prop="paymentState">-->
<!--        <el-input-->
<!--          v-model="queryParams.paymentState"-->
<!--          placeholder="请输入订阅的付款状态"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="用户取消订阅时间" prop="userCancellationTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.userCancellationTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择用户取消订阅时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订阅查询id" prop="subscriptionId">-->
<!--        <el-input-->
<!--          v-model="queryParams.subscriptionId"-->
<!--          placeholder="请输入订阅查询id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="包名" prop="packageName">-->
<!--        <el-input-->
<!--          v-model="queryParams.packageName"-->
<!--          placeholder="请输入包名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订阅查询token" prop="subscriptionToken">-->
<!--        <el-input-->
<!--          v-model="queryParams.subscriptionToken"-->
<!--          placeholder="请输入订阅查询token"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:subscription:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:subscription:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:subscription:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:subscription:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="subscriptionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="是否续订" align="center" prop="autoRenewing" />
      <el-table-column label="取消订阅原因" align="center" prop="cancelReason" width="180" />
      <el-table-column label="订阅产品的确认状态" align="center" prop="status" width="180"/>
      <el-table-column label="授予订阅时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订阅到期时间" align="center" prop="expireTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expireTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="自动续订时间" align="center" prop="autoResumeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.autoResumeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订阅价格" align="center" prop="price" />
      <el-table-column label="订阅的付款状态" align="center" prop="paymentState" width="180" />
      <el-table-column label="用户取消订阅时间" align="center" prop="userCancellationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.userCancellationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订阅查询id" align="center" prop="subscriptionId" width="180"/>
      <el-table-column label="包名" align="center" prop="packageName" />
      <el-table-column label="订阅查询token" align="center" prop="subscriptionToken" width="180"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:subscription:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:subscription:remove']"
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

    <!-- 添加或修改会员订阅信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="是否续订，0-不续订，1-续订" prop="autoRenewing">
          <el-input v-model="form.autoRenewing" placeholder="请输入是否续订，0-不续订，1-续订" />
        </el-form-item>
        <el-form-item label="0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅" prop="cancelReason">
          <el-input v-model="form.cancelReason" placeholder="请输入0。用户取消了订阅 1. 由于结算问题 2，系统取消了订阅 2. 订阅被新的订阅 3 取代了。开发者已取消订阅" />
        </el-form-item>
        <el-form-item label="授予订阅时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择授予订阅时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订阅到期时间" prop="expireTime">
          <el-date-picker clearable
            v-model="form.expireTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订阅到期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="自动续订时间" prop="autoResumeTime">
          <el-date-picker clearable
            v-model="form.autoResumeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择自动续订时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订阅价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入订阅价格" />
        </el-form-item>
        <el-form-item label="订阅的付款状态。可能的值包括：0。1. 待处理付款付款已收讫 2. 免费试用 3. 延迟升级/降级

已取消、已过期的订阅不会显示。" prop="paymentState">
          <el-input v-model="form.paymentState" placeholder="请输入订阅的付款状态。可能的值包括：0。1. 待处理付款付款已收讫 2. 免费试用 3. 延迟升级/降级

已取消、已过期的订阅不会显示。" />
        </el-form-item>
        <el-form-item label="用户取消订阅时间" prop="userCancellationTime">
          <el-date-picker clearable
            v-model="form.userCancellationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择用户取消订阅时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订阅查询id" prop="subscriptionId">
          <el-input v-model="form.subscriptionId" placeholder="请输入订阅查询id" />
        </el-form-item>
        <el-form-item label="包名" prop="packageName">
          <el-input v-model="form.packageName" placeholder="请输入包名" />
        </el-form-item>
        <el-form-item label="订阅查询token" prop="subscriptionToken">
          <el-input v-model="form.subscriptionToken" placeholder="请输入订阅查询token" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSubscription, getSubscription, delSubscription, addSubscription, updateSubscription } from "@/api/vip/subscription";

export default {
  name: "Subscription",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 会员订阅信息表格数据
      subscriptionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderId: null,
        autoRenewing: null,
        cancelReason: null,
        status: null,
        startTime: null,
        expireTime: null,
        autoResumeTime: null,
        price: null,
        paymentState: null,
        userCancellationTime: null,
        subscriptionId: null,
        packageName: null,
        subscriptionToken: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderId: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员订阅信息列表 */
    getList() {
      this.loading = true;
      listSubscription(this.queryParams).then(response => {
        this.subscriptionList = response.rows;
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
        id: null,
        orderId: null,
        autoRenewing: null,
        cancelReason: null,
        status: null,
        startTime: null,
        expireTime: null,
        autoResumeTime: null,
        price: null,
        paymentState: null,
        userCancellationTime: null,
        subscriptionId: null,
        packageName: null,
        subscriptionToken: null,
        createTime: null,
        updateTime: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加会员订阅信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSubscription(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员订阅信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSubscription(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSubscription(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除会员订阅信息编号为"' + ids + '"的数据项？').then(function() {
        return delSubscription(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/subscription/export', {
        ...this.queryParams
      }, `subscription_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
