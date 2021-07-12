// http://www.apache.org/licenses/LICENSE-2.0

namespace java shared
namespace cpp shared

struct SharedStruct {
  1: i32 key
  2: string value
}

service SharedService {
  SharedStruct getStruct(1: i32 key)
}