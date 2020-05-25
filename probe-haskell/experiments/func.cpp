int const Size = 5;
int const Half = Size / 2;

int arr[Size][Size] =
{
     1,  2,  3,  4,  5,
     6,  7,  8,  9, 11,
    12, 14, 15, 16, 17,
    18, 19, 20, 21, 22,
    23, 24, 25, 26, 27
};

int calcSumm()
{
    int res = 0;

    for (int i=0; i<Half; ++i)
    {
        for (int j=Half-i; j<=Half+i; j++)
        {
            res += arr[i][j];
            res += arr[Size-i-1][j];
        }
    }
    for (int i=0; i<Size; ++i)
    {
        res += arr[Half][i];
    }

    return res;
}


int calcSumm(int accumulator, int i, int j) {
   if ( i < size ) {
      calcSumm(accumulator + arr[i][j], i + 1, j);
   } else {
      calcSumm(accumulator + arr[i][j], 0, j + 1);
   } else {
      return accumulator + arr[i][j];
   }
}