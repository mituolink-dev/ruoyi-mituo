<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderSn">
        <el-input
          v-model="queryParams.orderSn"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="第三方订单编号" prop="thirdOrderSn">-->
<!--        <el-input-->
<!--          v-model="queryParams.thirdOrderSn"-->
<!--          placeholder="请输入第三方订单编号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="源订单号" prop="originOrderSn">
        <el-input
          v-model="queryParams.originOrderSn"
          placeholder="请输入源订单号，发起退款时关联的订单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品id" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="订单金额" prop="price">-->
<!--        <el-input-->
<!--          v-model="queryParams.price"-->
<!--          placeholder="请输入订单金额"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="分销渠道" prop="distributionChannel">-->
<!--        <el-input-->
<!--          v-model="queryParams.distributionChannel"-->
<!--          placeholder="请输入分销渠道"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="付款方式" prop="paymentMethod">-->
<!--        <el-input-->
<!--          v-model="queryParams.paymentMethod"-->
<!--          placeholder="请输入付款方式：0 Google pay "-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订单完成时间" prop="completeTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.completeTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择订单完成时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="退款时间" prop="refundTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.refundTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择退款时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="付款时间" prop="payTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.payTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择付款时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="设备ID" prop="deviceId">-->
<!--        <el-input-->
<!--          v-model="queryParams.deviceId"-->
<!--          placeholder="请输入设备ID"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="IP地址" prop="ip">-->
<!--        <el-input-->
<!--          v-model="queryParams.ip"-->
<!--          placeholder="请输入IP地址"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="国家" prop="country">-->
<!--        <el-input-->
<!--          v-model="queryParams.country"-->
<!--          placeholder="请输入国家"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否支持退款" prop="supportRefund">-->
<!--        <el-input-->
<!--          v-model="queryParams.supportRefund"-->
<!--          placeholder="请输入是否支持退款"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="退款原因" prop="refundReason">-->
<!--        <el-input-->
<!--          v-model="queryParams.refundReason"-->
<!--          placeholder="请输入退款原因"-->
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
          v-hasPermi="['system:order:add']"
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
          v-hasPermi="['system:order:edit']"
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
          v-hasPermi="['system:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="订单编号" align="center" prop="orderSn" />
      <el-table-column label="第三方订单编号" align="center" prop="thirdOrderSn" />
      <el-table-column label="源订单号" align="center" prop="originOrderSn" />
      <el-table-column label="订单类型" align="center" prop="type" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="商品id" align="center" prop="goodsId" />
      <el-table-column label="订单金额" align="center" prop="price" />
      <el-table-column label="分销渠道" align="center" prop="distributionChannel" />
      <el-table-column label="付款方式" align="center" prop="paymentMethod" />
      <el-table-column label="订单状态" align="center" prop="status" />
      <el-table-column label="发货状态" align="center" prop="deliverStatus" />
      <el-table-column label="订单完成时间" align="center" prop="completeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="退款时间" align="center" prop="refundTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refundTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="付款时间" align="center" prop="payTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.payTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="设备ID" align="center" prop="deviceId" />
      <el-table-column label="IP地址" align="center" prop="ip" />
      <el-table-column label="国家" align="center" prop="country" />
      <el-table-column label="第三方支付信息" align="center" prop="originInfo" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="是否支持退款" align="center" prop="supportRefund" />
      <el-table-column label="退款原因" align="center" prop="refundReason" />
      <el-table-column label="商品信息" align="center" prop="goodsInfo" />
      <el-table-column label="包名" align="center" prop="packageName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:order:remove']"
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

    <!-- 添加或修改会员订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderSn">
          <el-input v-model="form.orderSn" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="第三方订单编号" prop="thirdOrderSn">
          <el-input v-model="form.thirdOrderSn" placeholder="请输入第三方订单编号" />
        </el-form-item>
        <el-form-item label="源订单号，发起退款时关联的订单" prop="originOrderSn">
          <el-input v-model="form.originOrderSn" placeholder="请输入源订单号，发起退款时关联的订单" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="商品id" prop="goodsId">
          <el-input v-model="form.goodsId" placeholder="请输入商品id" />
        </el-form-item>
        <el-form-item label="订单金额" prop="price">
          <el-input v-model="form.price" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="分销渠道" prop="distributionChannel">
          <el-input v-model="form.distributionChannel" placeholder="请输入分销渠道" />
        </el-form-item>
        <el-form-item label="付款方式：0 Google pay " prop="paymentMethod">
          <el-input v-model="form.paymentMethod" placeholder="请输入付款方式：0 Google pay " />
        </el-form-item>
        <el-form-item label="订单完成时间" prop="completeTime">
          <el-date-picker clearable
            v-model="form.completeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退款时间" prop="refundTime">
          <el-date-picker clearable
            v-model="form.refundTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择退款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="付款时间" prop="payTime">
          <el-date-picker clearable
            v-model="form.payTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择付款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="设备ID" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备ID" />
        </el-form-item>
        <el-form-item label="IP地址" prop="ip">
          <el-input v-model="form.ip" placeholder="请输入IP地址" />
        </el-form-item>
        <el-form-item label="国家" prop="country">
          <el-input v-model="form.country" placeholder="请输入国家" />
        </el-form-item>
        <el-form-item label="第三方支付信息" prop="originInfo">
          <el-input v-model="form.originInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="是否支持退款" prop="supportRefund">
          <el-input v-model="form.supportRefund" placeholder="请输入是否支持退款" />
        </el-form-item>
        <el-form-item label="退款原因" prop="refundReason">
          <el-input v-model="form.refundReason" placeholder="请输入退款原因" />
        </el-form-item>
        <el-form-item label="商品信息" prop="goodsInfo">
          <el-input v-model="form.goodsInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="包名" prop="packageName">
          <el-input v-model="form.packageName" type="textarea" placeholder="请输入内容" />
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/system/order";

export default {
  name: "Order",
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
      // 会员订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderSn: null,
        thirdOrderSn: null,
        originOrderSn: null,
        type: null,
        userId: null,
        goodsId: null,
        price: null,
        distributionChannel: null,
        paymentMethod: null,
        status: null,
        deliverStatus: null,
        completeTime: null,
        refundTime: null,
        payTime: null,
        deviceId: null,
        ip: null,
        country: null,
        originInfo: null,
        supportRefund: null,
        refundReason: null,
        goodsInfo: null,
        packageName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        orderSn: [
          { required: true, message: "订单编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询会员订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderSn: null,
        thirdOrderSn: null,
        originOrderSn: null,
        type: null,
        userId: null,
        goodsId: null,
        price: null,
        distributionChannel: null,
        paymentMethod: null,
        status: null,
        deliverStatus: null,
        completeTime: null,
        refundTime: null,
        payTime: null,
        deviceId: null,
        ip: null,
        country: null,
        originInfo: null,
        remark: null,
        supportRefund: null,
        refundReason: null,
        goodsInfo: null,
        packageName: null,
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
      this.title = "添加会员订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除会员订单编号为"' + ids + '"的数据项？').then(function() {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
