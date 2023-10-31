#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int n, mn = 1e9;
string s;

void func(int cnt, vector<int> v)
{
    for(int i = 0;i<3;i++)
        v[i] = (v[i] + cnt) % 3;

    for(int i = 1;i<n-2;i++)
    {
        if((v[i-1] == 0 && v[i] == 1)
            || (v[i-1] == 1 && v[i] == 2)
            || (v[i-1] == 2 && v[i] == 0))
        {
            for(int j = i;j<i+3;j++)
                v[j] = (v[j] + 2) % 3;
            cnt += 2;
        }
        else if((v[i-1] == 0 && v[i] == 2)
            || (v[i-1] == 1 && v[i] == 0)
            || (v[i-1] == 2 && v[i] == 1))
        {
            for(int j = i;j<i+3;j++)
                v[j] = (v[j] + 1) % 3;
            cnt += 1;
        }
    }

    for(auto& e : v)
        if(e != v[0]) return;
        
    mn = min(mn, cnt);
}

int main()
{
    fastio;
    cin >> n >> s;
    vector<int> v(n);
    for (int i = 0; i < s.size(); i++)
    {
        if (s[i] == 'R')
            v[i] = 0;
        else if (s[i] == 'G')
            v[i] = 1;
        else if (s[i] == 'B')
            v[i] = 2;
    }
    // 1~3번째 전구를
    // 1) 그대로 두기
    // 2) 한 번 바꾸기
    // 3) 두 번 바꾸기
    for(int i = 0;i<3;i++)
        func(i, v);
    
    cout << (mn == 1e9 ? -1 : mn) << '\n';
}
