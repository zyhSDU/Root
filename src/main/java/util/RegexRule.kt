package util

/*
 * Copyright (C) 2014 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import java.util.ArrayList
import java.util.regex.Pattern

/**
 * @author hu
 */
class RegexRule {

    val isEmpty: Boolean
        get() = positive.isEmpty()

    private val positive = ArrayList<String>()
    private val negative = ArrayList<String>()

    constructor() {

    }

    constructor(rule: String) {
        addRule(rule)
    }

    constructor(rules: ArrayList<String>) {
        for (rule in rules) {
            addRule(rule)
        }
    }

    /**
     * 添加一个正则规则 正则规则有两种，正正则和反正则
     * URL符合正则规则需要满足下面条件： 1.至少能匹配一条正正则 2.不能和任何反正则匹配
     * 正正则示例：+a.*c是一条正正则，正则的内容为a.*c，起始加号表示正正则
     * 反正则示例：-a.*c时一条反正则，正则的内容为a.*c，起始减号表示反正则
     * 如果一个规则的起始字符不为加号且不为减号，则该正则为正正则，正则的内容为自身
     * 例如a.*c是一条正正则，正则的内容为a.*c
     *
     * @param rule 正则规则
     * @return 自身
     */
    fun addRule(rule: String): RegexRule {
        if (rule.isEmpty()) {
            return this
        }
        val pn = rule[0]
        val realrule = rule.substring(1)
        when (pn) {
            '+' -> addPositive(realrule)
            '-' -> addNegative(realrule)
            else -> addPositive(rule)
        }
        return this
    }


    /**
     * 添加一个正正则规则
     *
     * @param positiveregex
     * @return 自身
     */
    fun addPositive(positiveregex: String): RegexRule {
        positive.add(positiveregex)
        return this
    }


    /**
     * 添加一个反正则规则
     *
     * @param negativeregex
     * @return 自身
     */
    fun addNegative(negativeregex: String): RegexRule {
        negative.add(negativeregex)
        return this
    }


    /**
     * 判断输入字符串是否符合正则规则
     *
     * @param str 输入的字符串
     * @return 输入字符串是否符合正则规则
     */
    fun satisfy(str: String): Boolean {

        val state = 0
        for (nregex in negative) {
            if (Pattern.matches(nregex, str)) {
                return false
            }
        }

        var count = 0
        for (pregex in positive) {
            if (Pattern.matches(pregex, str)) {
                count++
            }
        }
        return count != 0

    }
}