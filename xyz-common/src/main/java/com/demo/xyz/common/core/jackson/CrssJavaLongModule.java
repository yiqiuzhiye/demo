/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.demo.xyz.common.core.jackson;

import com.demo.xyz.common.core.util.SnowFlakeIdSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;

/**
 * java 8 时间默认序列化
 * 
 * @author admin
 *
 */

public class CrssJavaLongModule extends SimpleModule {

	public CrssJavaLongModule() {
		super(PackageVersion.VERSION);
		this.addSerializer(Long.class, SnowFlakeIdSerializer.instance);
		this.addSerializer(Long.TYPE, SnowFlakeIdSerializer.instance);

	}

}
