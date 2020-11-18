package com.voc.system.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 14:05
 */
public interface ISort<PK, UPK> extends IGeneric<PK, UPK>, Comparable<ISort<PK, UPK>> {

    String SORT_INDEX = "sortIndex";

    /**
     * 获取排序
     *
     * @return Long
     */
    Long getSortIndex();

    /**
     * 设置排序
     *
     * @param sortIndex Long
     */
    void setSortIndex(Long sortIndex);

    /**
     * 排序比较
     *
     * @param support ISortEntity
     * @return int
     */
    @Override
    default int compareTo(ISort support) {
        if (support == null) {
            return -1;
        }

        return Long.compare(getSortIndex() == null ? 0 : getSortIndex(), support.getSortIndex() == null ? 0 : support.getSortIndex());
    }

}
