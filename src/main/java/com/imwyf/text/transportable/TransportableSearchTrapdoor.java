package com.imwyf.text.transportable;

import com.imwyf.text.SearchTrapdoor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.text.transportable
 * @Author: imwyf
 * @Date: 2023/5/10 15:35
 * @Description: 可进行传输的搜索陷门
 */
@Data
@NoArgsConstructor
public class TransportableSearchTrapdoor implements Serializable {
    private byte[] T1;
    private byte[] T2;
    private byte[] T3;
    private byte[] T4;
    private int keywordNumber;

    public TransportableSearchTrapdoor(SearchTrapdoor searchTrapdoor) {
        T1 = searchTrapdoor.getT1().toBytes();
        T2 = searchTrapdoor.getT2().toBytes();
        T3 = searchTrapdoor.getT3().toBytes();
        T4 = searchTrapdoor.getT4().toBytes();
        keywordNumber = searchTrapdoor.getKeywordNumber();
    }
}
