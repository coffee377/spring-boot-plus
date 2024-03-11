import { reactive } from 'vue';
import { BasicTableProps, useTable } from '@/components/Table';
import { approvalColumns, approvalSearchSchema, columns, searchSchema } from './data';
import { message } from 'ant-design-vue';

export const useCommodityManagementTable = () => {
  /* 搜索条件 */
  const search = reactive<Record<string, any>>({});

  /* 配置表格 */
  const [registerTable, { reload, getForm }] = useTable({
    // api: (search) => commodityManagement.list(search), // 获取数据接口函数
    rowKey: 'id',
    columns,
    fetchSetting: {
      pageField: 'page_num',
      sizeField: 'page_size',
      listField: 'data',
      totalField: 'total',
    },
    formConfig: {
      labelWidth: 100,
      schemas: searchSchema,
      autoSubmitOnEnter: true,
      submitOnChange: false,
    },
    pagination: true,
    striped: true,
    useSearchForm: true,
    actionColumn: {
      fixed: 'left',
    },
    bordered: true,
    resizeHeightOffset: 35,
  } as BasicTableProps);

  const switchStatus = async (id: string | number) => {
    await commodityManagement.toggleStatus(id);
    message.success('状态修改成功');
    await reload();
  };

  return { search, registerTable, reload, getForm, download: commodityManagement.exportFile, switchStatus };
};

