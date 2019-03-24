/**
 * 
 */
package com.example.first.lang.convert;

/**
 * @author Bean
 *
 */
public interface IConvertPostHandler<PO, VO> {
	public void post(PO po, VO vo);
}
