/*
 * Copyright (C) 2007-2014 Geometer Plus <contact@geometerplus.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.geometerplus.zlibrary.text.view.style;

import java.util.List;

import org.geometerplus.zlibrary.core.fonts.FontEntry;
import org.geometerplus.zlibrary.text.model.ZLTextMetrics;
import org.geometerplus.zlibrary.text.view.ZLTextHyperlink;
import org.geometerplus.zlibrary.text.view.ZLTextStyle;

/**
 * 修饰样式，几乎所有的方法都得由实现类实现
 * 
 * @author chenjl
 * 
 */
public abstract class ZLTextDecoratedStyle extends ZLTextStyle
{
	// fields to be cached
	protected final ZLTextBaseStyle	BaseStyle;

	private List<FontEntry>			myFontEntries;
	private boolean					myIsItalic;
	private boolean					myIsBold;
	private boolean					myIsUnderline;
	private boolean					myIsStrikeThrough;
	private int						myLineSpacePercent;

	private boolean					myIsNotCached	= true;

	// add by cjl
	protected int					myFontSize;
	// private int myFontSize;
	// end by cjl
//	private int						mySpaceBefore;
//	private int						mySpaceAfter;
	private int						myVerticalAlign;
	private int						myLeftMargin;
	private int						myRightMargin;
	private int						myLeftPadding;
	private int						myRightPadding;
	private int						myFirstLineIndent;
	private ZLTextMetrics			myMetrics;
	// add by cjl
	private int						myLeftPaddingOnly;
	private int						myRightPaddingOnly;
	// end by cjl
	
	// add by yq
	private int						myTopMargin;
	private int						myBottomMargin;
	private int						myTopPadding;
	private int						myBottomPadding;
	private int						myTopPaddingOnly;
	private int						myBottomPaddingOnly;
	private int						myLeftBorder;
	private int						myRightBorder;
	private int						myTopBorder;
	private int						myBottomBorder;
	private int						myLeftBorderOnly;
	private int						myRightBorderOnly;
	private int						myTopBorderOnly;
	private int						myBottomBorderOnly;
	// end by yq

	protected ZLTextDecoratedStyle(ZLTextStyle base, ZLTextHyperlink hyperlink)
	{
		super(base, (hyperlink != null) ? hyperlink : base.Hyperlink);
		BaseStyle = base instanceof ZLTextBaseStyle
				? (ZLTextBaseStyle) base
				: ((ZLTextDecoratedStyle) base).BaseStyle;
	}

	private void initCache()
	{
		myFontEntries = getFontEntriesInternal();
		myIsItalic = isItalicInternal();
		myIsBold = isBoldInternal();
		myIsUnderline = isUnderlineInternal();
		myIsStrikeThrough = isStrikeThroughInternal();
		myLineSpacePercent = getLineSpacePercentInternal();

		myIsNotCached = false;
	}

	private void initMetricsCache(ZLTextMetrics metrics)
	{
		myMetrics = metrics;
		myFontSize = getFontSizeInternal(metrics);
//		mySpaceBefore = getSpaceBeforeInternal(metrics, myFontSize, true);
//		mySpaceAfter = getSpaceAfterInternal(metrics, myFontSize, true);
		myVerticalAlign = getVerticalAlignInternal(metrics, myFontSize);
		myLeftMargin = getLeftMarginInternal(metrics, myFontSize);
		myRightMargin = getRightMarginInternal(metrics, myFontSize);
		myLeftPadding = getLeftPaddingInternal(metrics, myFontSize, true);
		myRightPadding = getRightPaddingInternal(metrics, myFontSize, true);
		myFirstLineIndent = getFirstLineIndentInternal(metrics, myFontSize);

		myLeftPaddingOnly = getLeftPaddingInternal(metrics, myFontSize, false);
		myRightPaddingOnly = getRightPaddingInternal(metrics, myFontSize, false);
		
		// add by yq
		myTopMargin = getTopMarginInternal(metrics, myFontSize);
		myBottomMargin = getBottomMarginInternal(metrics, myFontSize);
		myTopPadding = getTopPaddingInternal(metrics, myFontSize, true);
		myBottomPadding = getBottomPaddingInternal(metrics, myFontSize, true);
		myTopPaddingOnly = getTopPaddingInternal(metrics, myFontSize, false);
		myBottomPaddingOnly = getBottomPaddingInternal(metrics, myFontSize, false);
		myLeftBorder = getLeftBorderInternal(metrics, myFontSize, true);
		myLeftBorderOnly = getLeftBorderInternal(metrics, myFontSize, false);
		myRightBorder = getRightBorderInternal(metrics, myFontSize, true);
		myRightBorderOnly = getRightBorderInternal(metrics, myFontSize, false);
		myTopBorder = getTopBorderInternal(metrics, myFontSize, true);
		myTopBorderOnly = getTopBorderInternal(metrics, myFontSize, false);
		myBottomBorder = getBottomBorderInternal(metrics, myFontSize, true);
		myBottomBorderOnly = getBottomBorderInternal(metrics, myFontSize, false);
		// end by yq
	}

	@Override
	public final List<FontEntry> getFontEntries()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myFontEntries;
	}

	protected abstract List<FontEntry> getFontEntriesInternal();

	@Override
	public final int getFontSize(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myFontSize;
	}

	protected abstract int getFontSizeInternal(ZLTextMetrics metrics);

//	@Override
//	public final int getSpaceBefore(ZLTextMetrics metrics)
//	{
//		if (!metrics.equals(myMetrics)) {
//			initMetricsCache(metrics);
//		}
//		return mySpaceBefore;
//	}
//
//	protected abstract int getSpaceBeforeInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
//
//	@Override
//	public final int getSpaceAfter(ZLTextMetrics metrics)
//	{
//		if (!metrics.equals(myMetrics)) {
//			initMetricsCache(metrics);
//		}
//		return mySpaceAfter;
//	}
//
//	protected abstract int getSpaceAfterInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);

	@Override
	public final boolean isItalic()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myIsItalic;
	}

	protected abstract boolean isItalicInternal();

	@Override
	public final boolean isBold()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myIsBold;
	}

	protected abstract boolean isBoldInternal();

	@Override
	public final boolean isUnderline()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myIsUnderline;
	}

	protected abstract boolean isUnderlineInternal();

	@Override
	public final boolean isStrikeThrough()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myIsStrikeThrough;
	}

	protected abstract boolean isStrikeThroughInternal();

	@Override
	public final int getVerticalAlign(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myVerticalAlign;
	}

	protected abstract int getVerticalAlignInternal(ZLTextMetrics metrics, int fontSize);

	@Override
	public final int getLeftMargin(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myLeftMargin;
	}

	protected abstract int getLeftMarginInternal(ZLTextMetrics metrics, int fontSize);

	@Override
	public final int getRightMargin(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myRightMargin;
	}

	protected abstract int getRightMarginInternal(ZLTextMetrics metrics, int fontSize);

	@Override
	public final int getLeftPadding(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myLeftPadding;
	}

	protected abstract int getLeftPaddingInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);

	@Override
	public final int getRightPadding(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myRightPadding;
	}
	
	protected abstract int getRightPaddingInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
	
	// add by cjl
	@Override
	public final int getLeftPaddingOnly(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myLeftPaddingOnly;
	}

	@Override
	public final int getRightPaddingOnly(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myRightPaddingOnly;
	}

	// end by cjl

	@Override
	public final int getFirstLineIndent(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myFirstLineIndent;
	}

	protected abstract int getFirstLineIndentInternal(ZLTextMetrics metrics, int fontSize);

	@Override
	public final int getLineSpacePercent()
	{
		if (myIsNotCached) {
			initCache();
		}
		return myLineSpacePercent;
	}

	protected abstract int getLineSpacePercentInternal();
	
	// add by yq
	@Override
	public final int getTopMargin(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myTopMargin;
	}

	protected abstract int getTopMarginInternal(ZLTextMetrics metrics, int fontSize);
	
	public final int getBottomMargin(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myBottomMargin;
	}

	protected abstract int getBottomMarginInternal(ZLTextMetrics metrics, int fontSize);
	
	@Override
	public final int getTopPadding(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myTopPadding;
	}
	
	protected abstract int getTopPaddingInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
	
	@Override
	public final int getBottomPadding(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myBottomPadding;
	}
	
	protected abstract int getBottomPaddingInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
	
	@Override
	public final int getTopPaddingOnly(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myTopPaddingOnly;
	}

	@Override
	public final int getBottomPaddingOnly(ZLTextMetrics metrics)
	{
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myBottomPaddingOnly;
	}
	
	@Override
	public final int getLeftBorder(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myLeftBorder;
	}
	
	@Override
	public final int getLeftBorderOnly(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myLeftBorderOnly;
	}
	
	protected abstract int getLeftBorderInternal(ZLTextMetrics metrics, int fontSize , boolean containParent);

	@Override
	public final int getRightBorder(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myRightBorder;
	}
	
	@Override
	public final int getRightBorderOnly(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myRightBorderOnly;
	}
	
	protected abstract int getRightBorderInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
	
	@Override
	public final int getTopBorder(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myTopBorder;
	}
	
	@Override
	public final int getTopBorderOnly(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myTopBorderOnly;
	}
	protected abstract int getTopBorderInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);

	@Override
	public final int getBottomBorder(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myBottomBorder;
	}
	
	@Override
	public final int getBottomBorderOnly(ZLTextMetrics metrics) {
		if (!metrics.equals(myMetrics)) {
			initMetricsCache(metrics);
		}
		return myBottomBorderOnly;
	}
	protected abstract int getBottomBorderInternal(ZLTextMetrics metrics, int fontSize, boolean containParent);
	// end by yq
}