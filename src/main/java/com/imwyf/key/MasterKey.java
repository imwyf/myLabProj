package com.imwyf.key;

import it.unisa.dia.gas.jpbc.Element;
import lombok.Data;

/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.common.key
 * @Author: imwyf
 * @Date: 2023/5/9 16:38
 * @Description: 系统主密钥
 */
@Data
public class MasterKey {
    private Element alpha;
    private Element beta;
}
