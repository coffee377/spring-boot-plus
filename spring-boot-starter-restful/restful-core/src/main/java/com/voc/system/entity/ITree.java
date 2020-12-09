package com.voc.system.entity;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2018/11/09 14:19
 */
public interface ITree<PK, UPK> extends IGeneric<PK, UPK> {
    /**
     * 根菜单的父ID
     */
    String ROOT_PARENT_ID = "ROOT";

    String PARENT_ID = "parentId";

    String TREE_PATH = "treePath";

    String TREE_LEVEL = "treeLevel";

    String SEPARATOR = "-";

    int PATH_LENGTH = 4;

    Pattern SEPARATOR_PATTERN = Pattern.compile(SEPARATOR);

    /**
     * 获取父节点ID
     *
     * @return PK
     */
    PK getParentId();

    /**
     * 设置父节点ID
     *
     * @param parentId PK
     */
    void setParentId(PK parentId);

    /**
     * 获取树型结构编码
     *
     * @return String
     */
    String getTreePath();

    /**
     * 设置树型结构编码
     *
     * @param treePath String
     */
    void setTreePath(String treePath);

    /**
     * 获取节点级别
     *
     * @return Integer
     */
    Integer getTreeLevel();

    /**
     * 设置节点级别
     *
     * @param treeLevel Integer
     */
    void setTreeLevel(Integer treeLevel);

    /**
     * 获取子节点
     *
     * @param <T> T
     * @return <T extends ITreeEntity<PK>>
     */
    <T extends ITree<PK, UPK>> List<T> getChildren();

    /**
     * 根据path获取节点级别
     *
     * @param treePath String
     * @return Integer
     */
    static int getLevel4Path(String treePath) {
        if (StringUtils.isEmpty(treePath)) {
            return 1;
        }
        Stream<String> stringStream = SEPARATOR_PATTERN.splitAsStream(treePath);
        long count = stringStream.filter(it -> it != null && !"".equals(it)).count();
        return (int) count;
    }

    /**
     * 是否 TreePath 格式
     *
     * @param treePath String
     * @return boolean
     */
    static boolean isTreePathFormat(String treePath) {
        if (!StringUtils.isEmpty(treePath)) {
            Stream<String> stringStream = SEPARATOR_PATTERN.splitAsStream(treePath)
                    .filter(it -> it != null && !"".equals(it));
            return stringStream.allMatch(
                    it -> it.length() == PATH_LENGTH
            );
        }
        return false;
    }

    /**
     * 根据当前节点path获取父节点的path
     *
     * @param currentPath String
     * @return 父节点path
     */
    static String getParentPath(String currentPath) {
        if (currentPath == null || currentPath.length() < PATH_LENGTH) {
            return null;
        }
        return currentPath.substring(0, currentPath.length() - PATH_LENGTH - SEPARATOR.length());
    }

    /**
     * 根据父节点path生成当前节点path
     *
     * @param parentPath 父节点path
     * @return String
     */
    static String generatePathByParentPath(String parentPath) {
        StringBuffer buffer;
        buffer = new StringBuffer();
        if (!StringUtils.isEmpty(parentPath)) {
            buffer.append(parentPath).append(SEPARATOR);
        }
        // TODO: 2020/10/19 21:38 生成编码
//        buffer.append(IDGenerator.RANDOM_TREE_PATH.generate());
        return buffer.toString();
    }

//    /**
//     * 根据父节点ID生成 TreePath
//     *
//     * @param list     Collection<T>
//     * @param parentId 父节点ID
//     * @param <T>      T extends ITreeEntity
//     * @return String
//     */
//    static <T extends ITreeEntity> String generatePathByParentId(Collection<T> list, String parentId) {
//        String parentTreePath = null;
//        //非根路径生成，父节点 treePath 为空抛出异常
//        if (isNotRootMenu(parentId)) {
//            // bug fix npe: parentPath is null
//            parentTreePath = list.stream().filter(
//                    it -> it.getId().equals(parentId)
//            ).map(ITreeEntity::getTreePath).collect(Collectors.toList()).get(0);
//
//            if (StringUtils.isEmpty(parentTreePath)) {
//                throw new NotFoundException("treePath with menu id " + parentId + " is empty");
//            }
//        }
//        return generatePathByParentPath(parentTreePath);
//    }

//    /**
//     * 树型结构编码及级别校验
//     *
//     * @param list       Collection<T>
//     * @param treeEntity T
//     * @param <T>        T extends ITreeEntity
//     * @return boolean     是否修改
//     */
//    static <T extends ITreeEntity> boolean treePathAndLevelCheck(Collection<T> list, T treeEntity) {
//        List<String> treePaths = list.stream().filter(
//                it -> it.getId().equals(treeEntity.getParentId())
//        ).map(ITreeEntity::getTreePath).collect(Collectors.toList());
//        String parentTreePath = treePaths.isEmpty() ? null : treePaths.get(0);
//        return treePathAndLevelCheck(parentTreePath, treeEntity);
//    }
//
//    /**
//     * 树型结构编码及级别校验
//     *
//     * @param parentTreePath 父节点 treePath
//     * @param treeEntity     T
//     * @param <T>            T extends ITreeEntity
//     * @return boolean     是否修改
//     */
//    static <T extends ITreeEntity> boolean treePathAndLevelCheck(String parentTreePath, T treeEntity) {
//        //计算的 treePath
//        String calTreePath = ITreeEntity.generatePathByParentPath(parentTreePath);
//        //当前菜单 treePath
//        String treePath = treeEntity.getTreePath();
//        Object parentId = treeEntity.getParentId();
//        if (parentId instanceof String) {
//            String pid = (String) parentId;
//            String checkPath = treePath;
//            //非treePath格式字符串
//            if (!isTreePathFormat(treePath)) {
//                checkPath = calTreePath;
//            }
//
//            if (isNotRootMenu(pid) && isTreePathFormat(treePath)) {
//                if (!StringUtils.isEmpty(parentTreePath) && parentTreePath.equals(getParentPath(treePath))) {
//                    checkPath = null;
//                } else {
//                    checkPath = calTreePath;
//                }
//            }
//
//            if (!StringUtils.isEmpty(checkPath) && !checkPath.equals(treePath)) {
//                treeEntity.setTreePath(checkPath);
//                treeEntity.setTreeLevel(getLevel4Path(checkPath));
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 判断是否是根节点
     *
     * @param parentId 父id
     * @return boolean
     */
    static boolean isRootNode(String parentId) {
        return StringUtils.isEmpty(parentId) || ROOT_PARENT_ID.equalsIgnoreCase(parentId);
    }

    /**
     * 树形结构循环迭代
     * <T extends ITree<PK, UPK>> List<T>
     *
     * @param list     Collection<T>
     * @param consumer Consumer<T>
     * @param <T>      T extends ITreeEntity
     */
    @SuppressWarnings("unchecked")
    static <T extends ITree> void forEach(List<T> list, Consumer<T> consumer) {
        list.forEach(node -> {
            consumer.accept(node);
            List children = node.getChildren();
            if (children != null && children.size() > 0) {
                forEach((List<T>) children, consumer);
            }
        });
    }

    /**
     * 集合转为树形结构,返回根节点集合
     *
     * @param dataList      需要转换的集合
     * @param childConsumer 设置子节点回调
     * @param <N>           树节点类型
     * @param <PK>          主键类型
     * @return 树形结构集合
     */
    static <N extends ITree<PK, ?>, PK> List<N> list2Tree(Collection<N> dataList,
                                                          BiConsumer<N, List<N>> childConsumer) {
        return list2Tree(dataList, childConsumer,
                (Function<INode<N, PK>, Predicate<N>>) predicate -> node -> node == null || predicate.getNode(node.getParentId()) == null);
    }

    /**
     * 集合转为树形结构,返回指定节点集合
     *
     * @param dataList      需要转换的集合
     * @param childConsumer 设置子节点回调
     * @param nodePredicate 节点判断
     * @param <N>           树节点类型
     * @param <PK>          主键类型
     * @return 树形结构集合
     */
    static <N extends ITree<PK, ?>, PK> List<N> list2Tree(Collection<N> dataList,
                                                          BiConsumer<N, List<N>> childConsumer,
                                                          Predicate<N> nodePredicate) {
        return list2Tree(dataList, childConsumer, (Function<INode<N, PK>, Predicate<N>>) predicate -> nodePredicate);
    }

    /**
     * 列表结构转为树结构,并返回根节点集合
     *
     * @param dataList          数据集合
     * @param childConsumer     子节点消费接口,用于设置子节点
     * @param predicateFunction 节点判断函数,传入helper,获取一个判断是否为指定节点的函数
     * @param <N>               元素类型
     * @param <PK>              主键类型
     * @return 根节点集合
     */
    static <N extends ITree<PK, ?>, PK> List<N> list2Tree(final Collection<N> dataList,
                                                          final BiConsumer<N, List<N>> childConsumer,
                                                          final Function<INode<N, PK>, Predicate<N>> predicateFunction) {
        Objects.requireNonNull(dataList, "source list can not be null");
        Objects.requireNonNull(childConsumer, "child consumer can not be null");
        Objects.requireNonNull(predicateFunction, "root predicate function can not be null");

        Supplier<Stream<N>> streamSupplier = () -> dataList.size() < 1000 ? dataList.stream() : dataList.parallelStream();
        /*id,node,构建菜单缓存*/
        Map<PK, N> cache = new HashMap<>(0);
        /*parentId,children*/
        Map<PK, List<N>> treeCache = streamSupplier.get()
                .peek(node -> cache.put(node.getId(), node))
                .sorted()
                .collect(Collectors.groupingBy(ITree::getParentId));

        Predicate<N> nodePredicate = predicateFunction.apply(new INode<N, PK>() {
            @Override
            public List<N> getChildren(PK parentId) {
                return treeCache.get(parentId);
            }

            @Override
            public N getNode(PK id) {
                return cache.get(id);
            }

        });

        return streamSupplier.get()
                /* 设置每个节点的子节点 */
                .peek(
                        node ->
                                childConsumer.accept(node, treeCache.get(node.getId()))
                )
                /* 获取指定节点 */
                .filter(nodePredicate)
                .sorted()
                .collect(Collectors.toList());
    }

//
//    static <T extends ITreeEntity<PK>, PK> void expandTree2List(T parent, List<T> target, IDGenerator<PK> idGenerator) {
//        expandTree2List(parent, target, idGenerator, null);
//    }
//
//    /**
//     * 将树形结构转为列表结构，并填充对应的数据。<br>
//     * 如树结构数据： {name:'父节点',children:[{name:'子节点1'},{name:'子节点2'}]}<br>
//     * 解析后:[{id:'id1',name:'父节点',path:'<b>aoSt</b>'},{id:'id2',name:'子节点1',path:'<b>aoSt</b>-oS5a'},{id:'id3',name:'子节点2',path:'<b>aoSt</b>-uGpM'}]
//     *
//     * @param parent      树结构的根节点
//     * @param target      目标集合,转换后的数据将直接添加({@link List#add(Object)})到这个集合.
//     * @param <T>         继承{@link ITreeEntity}的类型
//     * @param idGenerator ID生成策略
//     * @param <PK>        主键类型
//     */
//    static <T extends ITreeEntity<PK>, PK> void expandTree2List(T parent, List<T> target, IDGenerator<PK> idGenerator,
//                                                                BiConsumer<T, List<T>> childConsumer) {
//
//        List<T> children = parent.getChildren();
//        if (childConsumer != null) {
//            childConsumer.accept(parent, new ArrayList<>());
//        }
//        target.add(parent);
//        if (parent.getTreePath() == null) {
//            parent.setTreePath(RandomUtil.randomChar(4));
//            if (parent.getTreePath() != null) {
//                parent.setTreeLevel(parent.getTreePath().split("-").length);
//            }
//            if (parent instanceof ISortEntity) {
//                Long index = ((ISortEntity) parent).getSortIndex();
//                if (null == index) {
//                    ((ISortEntity) parent).setSortIndex(1L);
//                }
//            }
//        }
//        if (children != null) {
//            PK pid = parent.getId();
//            if (pid == null) {
//                pid = idGenerator.generate();
//                parent.setId(pid);
//            }
//            for (int i = 0; i < children.size(); i++) {
//                T child = children.get(i);
//                if (child instanceof ISortEntity && parent instanceof ISortEntity) {
//                    Long index = ((ISortEntity) parent).getSortIndex();
//                    if (null == index) {
//                        ((ISortEntity) parent).setSortIndex(index = 1L);
//                    }
//                    ((ISortEntity) child).setSortIndex(new BigDecimal(index + "0" + (i + 1)).longValue());
//                }
//                child.setParentId(pid);
//                child.setTreePath(parent.getTreePath() + "-" + RandomUtil.randomChar(4));
//                child.setTreeLevel(child.getTreePath().split("-").length);
//
//                expandTree2List(child, target, idGenerator, childConsumer);
//            }
//        }
//    }


}
