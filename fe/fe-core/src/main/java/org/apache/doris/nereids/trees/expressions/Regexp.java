// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.nereids.trees.expressions;

import org.apache.doris.nereids.trees.expressions.visitor.ExpressionVisitor;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * like expression: a regexp '^xxx$'.
 */
public class Regexp extends StringRegexPredicate {

    public Regexp(Expression left, Expression right) {
        super("regexp", ImmutableList.of(left, right));
    }

    private Regexp(List<Expression> children) {
        super("regexp", children);
    }

    @Override
    public Regexp withChildren(List<Expression> children) {
        Preconditions.checkArgument(children.size() == 2);
        return new Regexp(children);
    }

    public <R, C> R accept(ExpressionVisitor<R, C> visitor, C context) {
        return visitor.visitRegexp(this, context);
    }
}
