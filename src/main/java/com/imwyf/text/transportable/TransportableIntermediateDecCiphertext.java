package com.imwyf.text.transportable;

import com.imwyf.text.IntermediateDecCiphertext;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @BelongsProject: Abe_Se
 * @BelongsPackage: com.imwyf.text.transportable
 * @Author: imwyf
 * @Date: 2023/5/10 16:51
 * @Description: 可传输的中间解密密文
 */
@Data
@NoArgsConstructor
public class TransportableIntermediateDecCiphertext implements Serializable {
    private byte[] C;
    private byte[] C1;
    private byte[] PCT;

    public TransportableIntermediateDecCiphertext(IntermediateDecCiphertext intermediateDecCiphertext){
        this.C = intermediateDecCiphertext.getC().toBytes();
        this.C1 = intermediateDecCiphertext.getC1().toBytes();
        this.PCT = intermediateDecCiphertext.getPCT().toBytes();
    }
}
