package src.model;

import java.util.HashMap;

public class TextHandler {

    private static int[][] charSet = {{0,' '},{0xA1,'0'},{0xA2,'1'},{0xA3,'2'},{0xA4,'3'},{0xA5,'4'},{0xA6,'5'},{0xA7,'6'},{0xA8,'7'},{0xA9,'8'},{0xAA,'9'},{0xAB,'!'},{0xAC,'?'},{0xAD,'.'},{0xAE,'-'},
                                    {0xB8,','},{0xBA,'/'},{0xBB,'A'},{0xBC,'B'},{0xBD,'C'},{0xBE,'D'},{0xBF,'E'},
                                    {0xC0,'F'},{0xC1,'G'},{0xC2,'H'},{0xC3,'I'},{0xC4,'J'},{0xC5,'K'},{0xC6,'L'},{0xC7,'M'},{0xC8,'N'},{0xC9,'O'},{0xCA,'P'},{0xCB,'Q'},{0xCC,'R'},{0xCD,'S'},{0xCE,'T'},{0xCF,'U'},
                                    {0xD0,'V'},{0xD1,'W'},{0xD2,'X'},{0xD3,'Y'},{0xD4,'Z'},{0xD5,'a'},{0xD6,'b'},{0xD7,'c'},{0xD8,'d'},{0xD9,'e'},{0xDA,'f'},{0xDB,'g'},{0xDC,'h'},{0xDD,'i'},{0xDE,'j'},{0xDF,'k'},
                                    {0xE0,'l'},{0xE1,'m'},{0xE2,'n'},{0xE3,'o'},{0xE4,'p'},{0xE5,'q'},{0xE6,'r'},{0xE7,'s'},{0xE8,'t'},{0xE9,'u'},{0xEA,'v'},{0xEB,'w'},{0xEC,'x'},{0xED,'y'},{0xEE,'z'},
                                    {0xF1,142},{0xF2,153},{0xF3,154},{0xF4,132},{0xF5,148},{0xF6,129}};

    public static char convertByteToChar(int hex){
        for(int i = 0;i<charSet.length;i++){
            if(charSet[i][0] == hex){
                return (char)charSet[i][1];
            }
        }
        return 35;//error is a # sign
    }

    public static int convertCharToByte(char c){
        for(int i = 0;i<charSet.length;i++){
            if(c == charSet[i][1]){
                return charSet[i][0];
            }
        }
        return 0xA1;//error is a 0
    }

}
