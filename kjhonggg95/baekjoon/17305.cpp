#include <bits/stdc++.h>
#define fastio ios::sync_with_stdio(false), cin.tie(0), cout.tie(0)
#define X first
#define Y second
using namespace std;
using ll = long long;
using pii = pair<int, int>;

vector<int> a;
vector<int> b;

ll three[250001];
ll five[250001];

int main()
{
    fastio;
    int n, w;
    cin >> n >> w;
    while (n--)
    {
        int t, s;
        cin >> t >> s;
        if (t == 3)
            a.push_back(s);
        else
            b.push_back(s);
    }

    sort(a.begin(), a.end(), greater<>());
    sort(b.begin(), b.end(), greater<>());

    for (int i = 0; i < a.size(); i++)
        three[i + 1] = three[i] + a[i];

    for (int i = 0; i < b.size(); i++)
        five[i + 1] = five[i] + b[i];

    int fiveCnt = min((int)b.size(), w / 5); // 최대 담을 수 있는 5g 짜리 사탕의 개수 -> 2
    ll sum = five[fiveCnt]; // 180

    while(fiveCnt >= 0)
    {
        int threeCnt = min((int)a.size(), (w - 5 * fiveCnt) / 3);
        sum = max(sum, five[fiveCnt] + three[threeCnt]);
        fiveCnt--;
    }

    cout << sum << '\n';
}
