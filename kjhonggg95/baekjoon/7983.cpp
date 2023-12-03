#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

int main()
{
    fastio;
    int n;
    cin >> n;
    vector<pii> v(n); // {마감일, 걸리는 시간}
    for(int i = 0;i<n;i++)
        cin >> v[i].Y >> v[i].X;
    sort(v.begin(), v.end(), greater<>());

    int last = 1e9 + 10;

    for(auto& e : v)
    {
        if(e.X < last)
            last = e.X - e.Y;
        else
            last -= e.Y;
    }

    cout << last << '\n';
}
