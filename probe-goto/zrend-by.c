#include<cstdio>
#include<cassert>

const long AX = 15;
const long AY =  4;

//Тут всякая мутотень
const          long W_[12]={0,2,6,14,22,30,38,46,62,78,94,110};
const unsigned long F_[126]  =  {0x01010102,0xff000100,0x01020304,0x00000000,
0x00000000,0x01020304,0x01020101,0x00000102,0x01020101,0x0000fffe,0x00000102,
0x01020101,0x01020202,0x00ff0001,0x01010102,0x00010202,0x00010202,0x010000ff,
0x01010102,0x00fffefe,0x00010202,0x01010102,0x00010202,0x01000001,0x01010100,
0x00010202,0x00010202,0x01010100,0x01000001,0x00010202,0x00000102,0x01020202,
0x01020000,0x00000102,0x01020202,0x0000fffe,0x01020202,0x00000102,0x00010102,
0x01010202,0x00010102,0x0100ffff,0x01010202,0x00fffffe,0x01010202,0x00010102,
0x00000001,0x01020303,0x01010101,0x00fffefd,0x00010203,0x01010101,0x00010203,
0x01000000,0x01000000,0x00010203,0x01010101,0x00010203,0x01020303,0x000000ff,
0x01020303,0x00000001,0x00010102,0x01010000,0x01010202,0x00010100,0x00010102,
0x01000101,0x01020201,0x0000ffff,0x00000101,0x01020100,0x00010101,0x01000102,
0x00000101,0x01020201,0x00010101,0x010100ff,0x01010102,0x0100ffff,0x01010102,
0x00010201,0x01010102,0xff000101,0x01010102,0x00fffeff,0x01010202,0xff000001,
0x00010102,0x0100ff00,0x01010202,0x010000ff,0x00010102,0x01010201,0x01020203,
0x00ff0000,0x01010203,0xff000000,0x01020203,0x00000100,0x01010203,0x01000000,
0x01010101,0xff000102,0x01000000,0x01010203,0x01010101,0x0100fffe,0x00000001,
0x01020302,0x01020203,0x00000101,0x01010203,0x00ffffff,0x01020203,0x0000ffff,
0x01010203,0x00010101,0x00010101,0x0100fffe,0x00000101,0x01020203,0x01010000,
0xff000102,0x00010101,0x01010203};
const long L_[12]={1,2,4,4,4,4,4,8,8,8,8,8};const char C_[]="*XITZUVWLPFYN";
long       A_[60];

long cnt(0),pfc,cur,xo[13],yo[13],key[12],knd[12];

#define A(x,y) (*(A_+(y)*AX+(x)))
#define X(n,k,c) (c?(long)((signed char)((F_[W_[(n)]+(k<<1)  ]>>(32-8*(c)))&0xFF)):0)
#define Y(n,k,c) (c?(long)((signed char)((F_[W_[(n)]+(k<<1)+1]>>(32-8*(c)))&0xFF)):0)
#define Log(f)                                                              \
  fprintf((f),"fig.# %d\n",cnt);for(int y=0;y<AY;++y){for(int x=0;x<AX;++x){\
  fputc(C_[A(x,y)],(f));}fputc('\n',(f));}fputc('\n',(f));

//Тут мутотень закончилась, дальше можно работать

int main(){
  assert(AX*AY == 60);
  assert(AX>AY);

  long i,x,y;
  
  pfc = -1; cur = 0; xo[0] = 0; yo[0] = 0; goto label2070;

label2070:
  key[cur] = -1; goto label2180;

label2090:
  for(i = 0; i <= pfc; ++i)if(key[cur]==key[i])goto label2180;

label2140:
  long x_[5],y_[5];
  for(i = 0; i < 5; ++i){
    x_[i] = xo[cur] + X(key[cur], knd[cur], i);
    y_[i] = yo[cur] + Y(key[cur], knd[cur], i);
    if(!i)continue;
    if((x_[i]<0)||(y_[i]<0)||(x_[i]>=AX)||(y_[i]>=AY)||(A(x_[i],y_[i])))goto label2160;
  }
  for(i = 0; i < 5; ++i)A(x_[i], y_[i]) = key[cur] + 1;
  for(x = 0; x < AX; ++x)for(y = 0; y < AY; ++y)if(!A(x,y)){
    xo[pfc + 2] = x; yo[pfc + 2] = y; goto label2260;
  }

label2260:
  ++pfc; cur = pfc + 1; if(pfc < 11)goto label2070;
  ++cnt;
  Log(stdout);
  goto label2240;

label2160:
  ++knd[cur]; if(knd[cur] < L_[key[cur]])goto label2140;

label2180:
  ++key[cur]; knd[cur] = 0; if(key[cur] < 12)goto label2090;
  if(pfc < 0){fprintf(stdout,"Complete\n");return 0;};

label2240:
  for(i = 0; i < 5; ++i)A(xo[pfc] + X(key[pfc], knd[pfc], i),
                          yo[pfc] + Y(key[pfc], knd[pfc], i))=0;
  --pfc; cur = pfc + 1; goto label2160;
}
