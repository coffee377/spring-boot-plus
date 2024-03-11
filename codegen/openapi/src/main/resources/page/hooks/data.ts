import { BasicColumn, FormSchema } from '@/components/Table';
import API from '@/api';

const colProps = { xl: 4, xxl: 4 };

/**
 * 商品管理表格配置
 */
export const columns: BasicColumn[] = [
  {
    title: '名称',
    dataIndex: 'name',
  },
  {
    title: '品牌',
    dataIndex: 'brand',
  },
  {
    title: '型号',
    dataIndex: 'model',
  },
  {
    title: '单位',
    dataIndex: 'unit',
  },
  {
    title: '参数',
    dataIndex: 'parameters',
  },
  {
    title: '类别',
    dataIndex: 'category',
  },
  {
    title: '备注',
    dataIndex: 'remark',
  },
  {
    title: '查找码',
    dataIndex: 'searchNo',
  },
  {
    title: '编码',
    dataIndex: 'code',
  },
  {
    title: '状态',
    dataIndex: 'status',
  },
];

const baseSearchSchema: FormSchema[] = [
  {
    field: 'name',
    label: '名称',
    component: 'Input',
    colProps,
    componentProps: {},
  },
  {
    field: 'category',
    label: '类别',
    component: 'ApiSelect',
    colProps,
    componentProps: {
      placeholder: '请选择商品类别',
      api: () => API.dict.getItemsByCode('SPGLLB'),
      labelField: 'name',
      valueField: 'value',
    },
  },
];

/**
 * 商品管理查询条件
 */
export const searchSchema: FormSchema[] = [
  ...baseSearchSchema,
  {
    field: 'status',
    label: '状态',
    component: 'Select',
    colProps,
    defaultValue: 'ENABLE',
    componentProps: {
      placeholder: '请选择商品状态',
      options: [
        {
          label: '启用',
          value: 'ENABLE',
        },
        {
          label: '禁用',
          value: 'DISABLE',
        },
      ],
    },
  },
];
