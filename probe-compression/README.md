# Probe compression

## File corpus

### War and Society

File                                  | Encoding | Lang | Raw size |   Gzip|   RAR | ZPAQ  | Zip   | BZip2 | Ratio |
--------------------------------------|----------|------|----------|-------|-------|-------|-------|-------|-------|
war-and-society-1-2-3-4.utf-8.txt     |Utf-8     |RU    |   6011930|1624869|1337852|1805337|1625033|1056481|0.1757 |
war-and-society-1-2-3-4.dict.trie     |Utf-8     |RU    |    584671| 114967|       |       |       |  77908|       |
war-and-society-1-2-3-4.graph.bin     |binary    | -    |4792890320|5250870|       |       |5411813| 589233|       |
war-and-society-1-2-3-4.markoff-events| -        | -    |         *|       |       |       |       |      *|       |
war-and-society-1-2-3-4.form-events   | -        | -    |         *|       |       |       |       |      *|       |


Where:
- Gzip : compressed in classic GZip method with default settings
- RAR : compressed with RAR 
- ZPAQ : compressed with ZPAQ
- ZIP : compressed with ZIP
- Bzip2 : compressed with Bzip2

## Grammatic & Semantic statistics

