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

package com.demo.xyz.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * 403 授权拒绝
 * 
 * @author admin
 *
 */
@NoArgsConstructor
public class CrssDeniedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8926461267995479617L;

	public CrssDeniedException(String message) {
		super(message);
	}

	public CrssDeniedException(Throwable cause) {
		super(cause);
	}

	public CrssDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public CrssDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
