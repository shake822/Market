package com.comtop.mobile.utils;
import java.security.Key;

/******************************************************************************
 * Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author zhaoqunqi
 * @since 1.0
 * @version 2014年8月13日 zhaoqunqi
 */
public abstract class AbstractCreateRSAKey {
    
    /**
     * 创建私有密钥（加密时用）
     * 
     * @return 私有密钥
     */
    protected abstract Key createRSAPrivateKey();
    
    /**
     * 创建公有密钥（解密时用）
     * 
     * @return 公有密钥
     */
    protected abstract Key createRSAPublicKey();
}
