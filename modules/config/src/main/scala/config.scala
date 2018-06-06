/*
 * Copyright 2017-2018 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package freestyle.rpc

import cats.effect.Sync
import pureconfig._
import com.typesafe.config.{Config, ConfigFactory}

object config {

  trait ConfigM[F[_]] {
    def load: F[Config]
  }

  implicit def syncConfigM[F[_]](implicit F: Sync[F]): ConfigM[F] = new ConfigM[F] {
    def load: F[Config] = F.delay(loadConfigOrThrow[Config](ConfigFactory.load()))
  }

}
