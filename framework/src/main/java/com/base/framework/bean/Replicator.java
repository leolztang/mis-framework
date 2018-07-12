package com.base.framework.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 对model和Dto的Page互相转换
 *
 * @param <D> dto
 * @param <M> model
 * @author tanglz
 */
public abstract class Replicator<D, M> {

    /**
     * 在List之间复制对象，以model列表数据源，复制到dto列表。
     *
     * @param models 数据源，model列表
     * @return List 复制出来的DTO列表
     */
    public final List<D> copyBeans(List<M> models) {
        List<D> targets = new ArrayList<>();
        if (models != null && !models.isEmpty()) {
            for (M bean : models) {
                if (bean != null) {
                    D target = copyModel(bean);
                    targets.add(target);
                } else {
                    throw new NullPointerException("对象复制失败！");
                }
            }
        }
        return targets;
    }

    public final List<M> copyDtos(List<D> dtos) {
        List<M> targets = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (D dto : dtos) {
                if (dto != null) {
                    M target = copyDto(dto);
                    targets.add(target);
                } else {
                    throw new NullPointerException("对象复制失败！");
                }
            }
        }
        return targets;
    }

    /**
     * DTO和Model之间的属性复制
     *
     * @param src model对象
     * @return dto对象
     */
    public abstract D copyModel(M src);

    /**
     * DTO和Model之间的属性复制
     *
     * @param dto dto 对象
     * @return model 对象
     */
    public abstract M copyDto(D dto);
}
