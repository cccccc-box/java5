USE [Vbooks]
GO
	/****** Object:  Table [dbo].[Authors]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Authors](
		[AuthorID] [int] IDENTITY(1, 1) NOT NULL,
		[Name] [nvarchar](255) NOT NULL,
		[Bio] [nvarchar](max) NULL,
		[ImagePath] [nvarchar](500) NULL,
		PRIMARY KEY CLUSTERED ([AuthorID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Books]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Books](
		[BookId] [int] IDENTITY(1, 1) NOT NULL,
		[Title] [nvarchar](255) NOT NULL,
		[AuthorId] [int] NOT NULL,
		[CategoryId] [int] NOT NULL,
		[PublisherId] [int] NOT NULL,
		[Price] [float] NOT NULL,
		[DiscountPercentage] [float] NOT NULL,
		[DiscountedPrice] [float] NOT NULL,
		[Quantity] [int] NOT NULL,
		[Description] [nvarchar](max) NULL,
		[ImagePath] [nvarchar](500) NOT NULL,
		CONSTRAINT [PK__Books__3DE0C207B97EC5BD] PRIMARY KEY CLUSTERED ([BookId] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Carts]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Carts](
		[CartID] [int] IDENTITY(1, 1) NOT NULL,
		[Amount] [int] NOT NULL,
		[BookID] [int] NOT NULL,
		[UserID] [int] NOT NULL,
		PRIMARY KEY CLUSTERED ([CartID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Categories]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Categories](
		[CategoryID] [int] IDENTITY(1, 1) NOT NULL,
		[Name] [nvarchar](255) NOT NULL,
		PRIMARY KEY CLUSTERED ([CategoryID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Discounts]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Discounts](
		[DiscountID] [int] IDENTITY(1, 1) NOT NULL,
		[Code] [nvarchar](20) NOT NULL,
		[Description] [varchar](255) NULL,
		[DiscountPercentage] [float] NULL,
		[StartDate] [date] NULL,
		[EndDate] [date] NULL,
		[Quantity] [int] NOT NULL,
		[Active] [bit] NOT NULL,
		[CreateAt] [datetime] NULL,
		[IsActive] [bit] NULL,
		PRIMARY KEY CLUSTERED ([DiscountID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Favorites]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Favorites](
		[FavoriteID] [int] IDENTITY(1, 1) NOT NULL,
		[UserID] [int] NOT NULL,
		[BookID] [int] NOT NULL,
		PRIMARY KEY CLUSTERED ([FavoriteID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[OrderDetails]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[OrderDetails](
		[OrderDetailID] [int] IDENTITY(1, 1) NOT NULL,
		[OrderID] [int] NOT NULL,
		[BookID] [int] NOT NULL,
		[Quantity] [int] NOT NULL,
		[Price] [float] NOT NULL,
		[DiscountedPrice] [float] NOT NULL,
		PRIMARY KEY CLUSTERED ([OrderDetailID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Orders]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Orders](
		[OrderID] [int] IDENTITY(1, 1) NOT NULL,
		[UserID] [int] NOT NULL,
		[RecipientName] [varchar](255) NULL,
		[Address] [nvarchar](255) NOT NULL,
		[Phone] [varchar](255) NULL,
		[OrderDate] [datetime] NULL,
		[TotalMerchandiseValue] [float] NOT NULL,
		[TotalAmount] [float] NOT NULL,
		[PaymentMethodID] [int] NOT NULL,
		[ProviderID] [int] NOT NULL,
		[DiscountID] [int] NULL,
		[Status] [int] NOT NULL,
		PRIMARY KEY CLUSTERED ([OrderID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[PaymentMethods]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[PaymentMethods](
		[PaymentMethodID] [int] IDENTITY(1, 1) NOT NULL,
		[MethodName] [nvarchar](100) NOT NULL,
		[Active] [bit] NOT NULL,
		[Description] [nvarchar](255) NULL,
		PRIMARY KEY CLUSTERED ([PaymentMethodID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Publishers]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Publishers](
		[PublisherID] [int] IDENTITY(1, 1) NOT NULL,
		[Name] [nvarchar](255) NOT NULL,
		PRIMARY KEY CLUSTERED ([PublisherID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Reviews]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Reviews](
		[ReviewID] [int] IDENTITY(1, 1) NOT NULL,
		[OrderID] [int] NOT NULL,
		[UserID] [int] NOT NULL,
		[BookID] [int] NOT NULL,
		[Rating] [int] NOT NULL,
		[Comment] [varchar](255) NULL,
		[ImagePath] [varchar](255) NULL,
		[CreateAt] [datetime] NULL,
		PRIMARY KEY CLUSTERED ([ReviewID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[ShippingProviders]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[ShippingProviders](
		[ProviderID] [int] IDENTITY(1, 1) NOT NULL,
		[Name] [nvarchar](100) NOT NULL,
		[Hotline] [varchar](255) NULL,
		[ShippingFee] [float] NOT NULL,
		[IsActive] [bit] NOT NULL,
		CONSTRAINT [PK__Shipping__B54C689D5DFA958B] PRIMARY KEY CLUSTERED ([ProviderID] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
	/****** Object:  Table [dbo].[Users]    Script Date: 4/20/2025 11:39:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO CREATE TABLE [dbo].[Users](
		[UserId] [int] IDENTITY(1, 1) NOT NULL,
		[FullName] [nvarchar](255) NOT NULL,
		[Email] [nvarchar](255) NOT NULL,
		[Password] [nvarchar](255) NOT NULL,
		[Phone] [varchar](15) NULL,
		[Address] [nvarchar](255) NOT NULL,
		[Role] [bit] NOT NULL,
		[Active] [bit] NOT NULL,
		CONSTRAINT [PK__Users__3214EC073217E7B3] PRIMARY KEY CLUSTERED ([UserId] ASC) WITH (
			PAD_INDEX = OFF,
			STATISTICS_NORECOMPUTE = OFF,
			IGNORE_DUP_KEY = OFF,
			ALLOW_ROW_LOCKS = ON,
			ALLOW_PAGE_LOCKS = ON,
			OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
		) ON [PRIMARY]
	) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Authors] ON
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		1,
		N 'Nguyễn Nhật Ánh',
		N 'Nhà văn nổi tiếng với các tác phẩm dành cho thanh thiếu niên như "Kính vạn hoa", "Mắt biếc".',
		N'nguyen-nhat-anh.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		2,
		N 'Nam Cao',
		N'Tác giả hiện thực phê phán với tác phẩm nổi bật "Chí Phèo".',
		N'nam_cao.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		3,
		N 'Ngô Tất Tố',
		N 'Nhà văn với tác phẩm "Tắt đèn", phản ánh cuộc sống nông dân Việt Nam trước Cách mạng.',
		N'ngo_tat_to.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		4,
		N'Vũ Trọng Phụng',
		N 'Tác giả nổi tiếng với "Số đỏ", phản ánh xã hội Việt Nam thời Pháp thuộc.',
		N'vu_trong_phung.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		5,
		N'Tô Hoài',
		N'Tác giả của "Dế Mèn phiêu lưu ký", một tác phẩm kinh điển cho thiếu nhi.',
		N'to_hoai.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		6,
		N 'Nguyễn Huy Thiệp',
		N 'Nhà văn hiện đại với các truyện ngắn mang tính hiện thực sâu sắc.',
		N'nguyen_huy_thiep.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		7,
		N 'Nguyễn Đình Thi',
		N 'Nhà thơ, nhà văn với bài thơ nổi tiếng "Đất nước".',
		N'nguyen_dinh_thi.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		8,
		N'Hồ Biểu Chánh',
		N 'Nhà văn tiên phong của dòng tiểu thuyết hiện đại miền Nam.',
		N'ho_bieu_chanh.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		9,
		N'Ma Văn Kháng',
		N'Tác giả của nhiều tiểu thuyết phản ánh hiện thực xã hội.',
		N'ma_van_khang.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		10,
		N'Phạm Tiến Duật',
		N 'Nhà thơ tiêu biểu của thời kỳ kháng chiến chống Mỹ.',
		N'pham_tien_duat.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		15,
		N'Hàn Mặc Tử 2',
		N'1.Ông có tài năng làm thơ từ rất sớm khi mới 16 tuổi. Ông đã từng gặp gỡ Phan Bội Châu và chịu ảnh hưởng khá lớn của chí sĩ này. Ông được Phan Bội Châu giới thiệu bài thơ Thức khuya của mình lên một tờ báo. Sau này, ông nhận một suất học bổng đi Pháp nhưng vì quá thân với Phan Bội Châu nên đành đình lại. Ông quyết định vào Sài Gòn lập nghiệp, năm ông 21 tuổi; lúc đầu làm ở Sở Đạc điền.',
		N'han_mac_tu.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		18,
		N'David J. Schwartz',
		N'David J. Schwartz (1927–1987) là một tác giả, diễn giả và giáo sư người Mỹ, nổi tiếng nhất với cuốn sách "The Magic of Thinking Big" (Tư Duy Lớn Để Thành Công), xuất bản năm 1959',
		N'David J. Schwartz.jpg'
	)
INSERT [dbo].[Authors] ([AuthorID], [Name], [Bio], [ImagePath])
VALUES (
		19,
		N'Dale Carnegie',
		N'Dale Carnegie (1888–1955) là một tác giả, giảng viên nổi tiếng người Mỹ, được biết đến với những cuốn sách về phát triển bản thân, kỹ năng giao tiếp và nghệ thuật lãnh đạo. Ông là người sáng lập Dale Carnegie Training – một tổ chức chuyên đào tạo kỹ năng mềm và phát triển con người.',
		N'Dale Carnegie.jpg'
	)
SET IDENTITY_INSERT [dbo].[Authors] OFF
GO
SET IDENTITY_INSERT [dbo].[Books] ON
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		1,
		N'Mắt biếc',
		1,
		3,
		2,
		120000,
		10,
		108000,
		48,
		N 'Tiểu thuyết nổi tiếng của Nguyễn Nhật Ánh về tình yêu tuổi học trò.',
		N'8934974178637.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		2,
		N'Chí Phèo',
		2,
		3,
		4,
		95000,
		5,
		90250,
		39,
		N 'Tác phẩm hiện thực phê phán kinh điển của Nam Cao.',
		N'9786044916972.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		3,
		N'Tắt đèn',
		3,
		3,
		4,
		85000,
		8,
		78200,
		35,
		N'Câu chuyện về thân phận người nông dân dưới chế độ thực dân.',
		N'tatden.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		4,
		N'Số đỏ',
		4,
		3,
		5,
		110000,
		12,
		96800,
		44,
		N 'Tác phẩm châm biếm sâu sắc xã hội Việt Nam thời Pháp thuộc.',
		N'so_do.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		5,
		N'Dế Mèn phiêu lưu ký',
		5,
		8,
		1,
		75000,
		15,
		63750,
		60,
		N'Câu chuyện phiêu lưu thú vị của chú dế Mèn.',
		N'de_men_phieu_luu_ky.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		6,
		N 'Truyện ngắn Nguyễn Huy Thiệp',
		6,
		3,
		6,
		130000,
		7,
		120900,
		30,
		N 'Tuyển tập truyện ngắn nổi tiếng của Nguyễn Huy Thiệp.',
		N'truyen_ngan_nguyen_huy_thiep.jpg'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		8,
		N'Con nhà nghèo',
		8,
		3,
		8,
		90000,
		5,
		85500,
		49,
		N'Một trong những tác phẩm nổi tiếng của Hồ Biểu Chánh.',
		N'con_nha_ngheo.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		12,
		N'Mắt Biếc test update',
		1,
		1,
		13,
		43000,
		15.000000953674316,
		36550,
		50,
		N'Không có mô tả',
		N'8934974178637.webp'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		15,
		N 'Dám Nghĩ Lớn',
		18,
		5,
		1,
		128000,
		27.000001907348633,
		93440,
		19,
		N 'Hãy thử nghĩ về những người có mức thu nhập cao hơn bạn gấp 5 lần. Có phải họ thông minh hơn bạn gấp 5 lần? Họ làm việc vất vả hơn bạn gấp 5 lần? Nếu
câu trả lời của bạn là “không” thì bạn sẽ chạm đến câu hỏi này: “Vậy, họ có những đức tính, phẩm chất hay bí quyết gì mà tôi không có?”',
		N'dam_nghi_lon.jpg'
	)
INSERT [dbo].[Books] (
		[BookId],
		[Title],
		[AuthorId],
		[CategoryId],
		[PublisherId],
		[Price],
		[DiscountPercentage],
		[DiscountedPrice],
		[Quantity],
		[Description],
		[ImagePath]
	)
VALUES (
		16,
		N'Máu Và Trăng',
		15,
		3,
		1,
		120000,
		25,
		90000,
		15,
		N'Với những minh họa sống động và đẹp đẽ, quyển sách này sẽ đưa bạn du ngoạn qua những vầng thơ bất hủ của Hàn Mặc Tử, trải dài từ Lệ Thanh Thi Tập, đến Gái Quê, và cuối cùng là Đau Thương (hay còn gọi là Thơ Điên).',
		N'mau_va_trang.webp'
	)
SET IDENTITY_INSERT [dbo].[Books] OFF
GO
SET IDENTITY_INSERT [dbo].[Carts] ON
INSERT [dbo].[Carts] ([CartID], [Amount], [BookID], [UserID])
VALUES (1, 2, 1, 17)
INSERT [dbo].[Carts] ([CartID], [Amount], [BookID], [UserID])
VALUES (2, 1, 8, 17)
SET IDENTITY_INSERT [dbo].[Carts] OFF
GO
SET IDENTITY_INSERT [dbo].[Categories] ON
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (10, N'Sách du lịch')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (1, N'Sách giáo trình')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (4, N'Sách khoa học')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (5, N'Sách kỹ năng')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (9, N'Sách nấu ăn')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (7, N'Sách tâm linh')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (2, N'Sách tham khảo')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (8, N'Sách thiếu nhi')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (6, N'Sách tôn giáo')
INSERT [dbo].[Categories] ([CategoryID], [Name])
VALUES (3, N'Sách văn học')
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Favorites] ON
INSERT [dbo].[Favorites] ([FavoriteID], [UserID], [BookID])
VALUES (1, 17, 2)
INSERT [dbo].[Favorites] ([FavoriteID], [UserID], [BookID])
VALUES (2, 17, 15)
SET IDENTITY_INSERT [dbo].[Favorites] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetails] ON
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (2, 1, 16, 1, 12000, 90000)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (3, 2, 15, 1, 12800, 93440)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (4, 2, 2, 1, 95000, 90250)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (15, 13, 8, 3, 90000, 85500)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (16, 14, 4, 1, 110000, 96800)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (17, 15, 2, 1, 95000, 90250)
INSERT [dbo].[OrderDetails] (
		[OrderDetailID],
		[OrderID],
		[BookID],
		[Quantity],
		[Price],
		[DiscountedPrice]
	)
VALUES (18, 16, 15, 1, 128000, 93440)
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		1,
		2,
		N'Tr?n Th? B',
		N 'Hồ Chí Minh, Việt Nam',
		N'0987654321',
		CAST(N'2025-04-19T19:15:44.710' AS DateTime),
		90000,
		120000,
		1,
		1,
		NULL,
		3
	)
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		2,
		2,
		N'Tr?n Th? B',
		N 'Hồ Chí Minh, Việt Nam',
		N'0987654321',
		CAST(N'2025-04-19T19:15:44.710' AS DateTime),
		183690,
		213690,
		1,
		1,
		NULL,
		2
	)
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		13,
		17,
		N'Sy',
		N 'Đà Nẵng 2',
		N'0987654321',
		CAST(N'2025-04-20T00:02:39.293' AS DateTime),
		256500,
		281500,
		1,
		2,
		NULL,
		2
	)
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		14,
		17,
		N'Sy',
		N 'Đà Nẵng',
		N'0987654321',
		CAST(N'2025-04-20T15:02:58.503' AS DateTime),
		96800,
		126800,
		1,
		1,
		NULL,
		2
	)
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		15,
		17,
		N'Sy',
		N 'Đà Nẵng',
		N'0987654321',
		CAST(N'2025-04-20T16:32:17.477' AS DateTime),
		90250,
		120250,
		1,
		1,
		NULL,
		0
	)
INSERT [dbo].[Orders] (
		[OrderID],
		[UserID],
		[RecipientName],
		[Address],
		[Phone],
		[OrderDate],
		[TotalMerchandiseValue],
		[TotalAmount],
		[PaymentMethodID],
		[ProviderID],
		[DiscountID],
		[Status]
	)
VALUES (
		16,
		2,
		N'Tr?n Th? B',
		N 'Hồ Chí Minh, Việt Nam',
		N'0987654321',
		CAST(N'2025-04-20T20:34:08.483' AS DateTime),
		93440,
		123440,
		1,
		1,
		NULL,
		3
	)
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[PaymentMethods] ON
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (1, N'COD', 1, N'Thanh toán khi nhận hàng')
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (2, N 'VNPay', 1, N 'Thanh toán qua cổng VNPay')
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		3,
		N'MoMo',
		1,
		N'Thanh toán qua ví điện tử Momo'
	)
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		4,
		N'PayPal',
		0,
		N'Thanh toán qu?c t? qua PayPal'
	)
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (5, N'ZaloPay', 0, N'Thanh toán qua ZaloPay')
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		6,
		N'Bank Transfer',
		0,
		N'Chuy?n kho?n ngân hàng'
	)
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		7,
		N'Credit Card',
		0,
		N'Thanh toán b?ng th? tín d?ng'
	)
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		8,
		N'ShopeePay',
		0,
		N'Thanh toán qua ví ShopeePay'
	)
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (9, N'Apple Pay', 0, N'Thanh toán qua Apple Pay')
INSERT [dbo].[PaymentMethods] (
		[PaymentMethodID],
		[MethodName],
		[Active],
		[Description]
	)
VALUES (
		10,
		N'Google Pay',
		0,
		N'Thanh toán qua Google Pay'
	)
SET IDENTITY_INSERT [dbo].[PaymentMethods] OFF
GO
SET IDENTITY_INSERT [dbo].[Publishers] ON
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (11, N'Công ty Cổ phần Sách Thái Hà')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (10, N 'Công ty Văn hóa & Truyền thông Nhã Nam')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (13, N 'Đại học quốc gia Hà Nội')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (9, N 'Nhà xuất bản Đại học Sư phạm')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (3, N 'Nhà xuất bản Giáo dục Việt Nam')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (5, N 'Nhà xuất bản Hội Nhà văn')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (8, N 'Nhà xuất bản Khoa học Xã hội')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (1, N 'Nhà xuất bản Kim Đồng')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (7, N 'Nhà xuất bản Lao động')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (6, N 'Nhà xuất bản Phụ nữ')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (2, N 'Nhà xuất bản Trẻ')
INSERT [dbo].[Publishers] ([PublisherID], [Name])
VALUES (4, N 'Nhà xuất bản Văn học')
SET IDENTITY_INSERT [dbo].[Publishers] OFF
GO
SET IDENTITY_INSERT [dbo].[Reviews] ON
INSERT [dbo].[Reviews] (
		[ReviewID],
		[OrderID],
		[UserID],
		[BookID],
		[Rating],
		[Comment],
		[ImagePath],
		[CreateAt]
	)
VALUES (
		2,
		1,
		2,
		16,
		4,
		N'Ma´u va` Trang cu?a Ha`n Ma?c Tu? qua´ hay',
		NULL,
		CAST(N'2025-04-19T19:18:01.863' AS DateTime)
	)
INSERT [dbo].[Reviews] (
		[ReviewID],
		[OrderID],
		[UserID],
		[BookID],
		[Rating],
		[Comment],
		[ImagePath],
		[CreateAt]
	)
VALUES (
		3,
		2,
		2,
		15,
		5,
		N'Mô?t quô´n sa´ch self help hay',
		NULL,
		CAST(N'2025-04-19T19:18:01.863' AS DateTime)
	)
INSERT [dbo].[Reviews] (
		[ReviewID],
		[OrderID],
		[UserID],
		[BookID],
		[Rating],
		[Comment],
		[ImagePath],
		[CreateAt]
	)
VALUES (
		4,
		2,
		2,
		2,
		5,
		N 'Ta´c Phâ?m kinh diê?n cu?a Nam Cao',
		NULL,
		CAST(N'2025-04-19T19:18:01.863' AS DateTime)
	)
SET IDENTITY_INSERT [dbo].[Reviews] OFF
GO
SET IDENTITY_INSERT [dbo].[ShippingProviders] ON
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (
		1,
		N 'Giao Hàng Nhanh (GHN)',
		N'1900636099',
		30000,
		1
	)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (
		2,
		N'Giao Hàng Tiết Kiệm (GHTK)',
		N'19006092',
		25000,
		1
	)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (3, N'Viettel Post', N'19008095', 35000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (4, N 'VNPost', N'1900545481', 20000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (5, N'J&T Express', N'19001088', 28000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (6, N 'Ninja Van', N'1900888654', 27000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (7, N'BEST Express', N'19001247', 26000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (8, N'AhaMove', N'1900545411', 22000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (9, N'Lalamove', N'1900636688', 24000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (10, N 'NowShip', N'19002042', 21000, 1)
INSERT [dbo].[ShippingProviders] (
		[ProviderID],
		[Name],
		[Hotline],
		[ShippingFee],
		[IsActive]
	)
VALUES (15, N'DHL', N'19002042', 12000, 0)
SET IDENTITY_INSERT [dbo].[ShippingProviders] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		1,
		N 'Nguyễn Văn A',
		N'nguyenvana@example.com',
		N'password123',
		N'0123456788',
		N 'Hà Nội, Việt Nam.',
		0,
		0
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		2,
		N'Trần Thị B',
		N'tranthib@example.com',
		N'password123',
		N'0987654321',
		N 'Hồ Chí Minh, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		3,
		N'Lê Văn D',
		N'levanc@example.com',
		N'password123',
		N'0345678912',
		N 'Đà Nẵng, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		4,
		N'Phạm Thị D',
		N'phamthid@example.com',
		N'password123',
		N'0765432198',
		N 'Cần Thơ, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		5,
		N'Hoàng Văn E',
		N'hoangvane@example.com',
		N'password123',
		N'0112233445',
		N 'Hải Phòng, Việt Nam',
		1,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		6,
		N'Đặng Thị F',
		N'dangthif@example.com',
		N'password123',
		N'0556677889',
		N 'Huế, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		7,
		N'Vũ Văn G',
		N'vuvang@example.com',
		N'password123',
		N'0223344556',
		N 'Nha Trang, Việt Nam',
		1,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		8,
		N'Bùi Thị H',
		N'buithih@example.com',
		N'password123',
		N'0998877665',
		N 'Vũng Tàu, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		9,
		N 'Ngô Văn I',
		N'ngovani@example.com',
		N'password123',
		N'0665544332',
		N 'Bình Dương, Việt Nam',
		1,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		10,
		N'Đỗ Thị K',
		N'dothik@example.com',
		N'password123',
		N'0776655443',
		N 'Hải Dương, Việt Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		11,
		N'Alăng Quân Sỹ',
		N'syaqpd09903@fpt.edu.vn',
		N'123456',
		N'0889228847',
		N 'Quảng Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		15,
		N'Trần Công Học',
		N'hoc@gmail.com',
		N'1234',
		N'09393993',
		N'Duy Xuyên',
		0,
		0
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		16,
		N'Test1',
		N'test1@example.com',
		N'123',
		N'0987654321',
		N'fdsfds',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		17,
		N'Sy',
		N'admin@admin.com',
		N'admin',
		N'0987654321',
		N 'Đà Nẵng',
		1,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		18,
		N'khach',
		N'khach@khach.com',
		N'khach',
		N'0987656567',
		N 'Hà Nội',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		19,
		N'test2',
		N'test2@example.com',
		N'test2',
		N'0978678676',
		N 'Quảng Nam',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		22,
		N'diheti2068@jomspar.com',
		N'diheti2068@jomspar.com',
		N'diheti2068@jomspar.com',
		N'543545345345',
		N'diheti2068@jomspar.com',
		0,
		1
	)
INSERT [dbo].[Users] (
		[UserId],
		[FullName],
		[Email],
		[Password],
		[Phone],
		[Address],
		[Role],
		[Active]
	)
VALUES (
		23,
		N'yominoc900@egvoo.com',
		N'yominoc900@egvoo.com',
		N'yominoc900@egvoo.com',
		N'432432432423',
		N'yominoc900@egvoo.com',
		0,
		1
	)
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UK_t8o6pivur7nn124jehx7cygw5]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Categories]
ADD CONSTRAINT [UK_t8o6pivur7nn124jehx7cygw5] UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UKt8o6pivur7nn124jehx7cygw5]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Categories]
ADD CONSTRAINT [UKt8o6pivur7nn124jehx7cygw5] UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__Categori__737584F600F9DAB4]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Categories]
ADD UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__Discount__A25C5AA744AEDA71]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Discounts]
ADD UNIQUE NONCLUSTERED ([Code] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__PaymentM__218CFB17576EB5C7]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[PaymentMethods]
ADD UNIQUE NONCLUSTERED ([MethodName] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UK_an1ucpx8sw2qm194mlok8e5us]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Publishers]
ADD CONSTRAINT [UK_an1ucpx8sw2qm194mlok8e5us] UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UKan1ucpx8sw2qm194mlok8e5us]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Publishers]
ADD CONSTRAINT [UKan1ucpx8sw2qm194mlok8e5us] UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__Publishe__737584F6100B9CDB]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Publishers]
ADD UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__Shipping__737584F6B0026D8B]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[ShippingProviders]
ADD CONSTRAINT [UQ__Shipping__737584F6B0026D8B] UNIQUE NONCLUSTERED ([Name] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UK_6dotkott2kjsp8vw4d0m25fb7]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Users]
ADD CONSTRAINT [UK_6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED ([Email] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
	/****** Object:  Index [UQ__Users__A9D105348D352492]    Script Date: 4/20/2025 11:39:38 PM ******/
ALTER TABLE [dbo].[Users]
ADD CONSTRAINT [UQ__Users__A9D105348D352492] UNIQUE NONCLUSTERED ([Email] ASC) WITH (
		PAD_INDEX = OFF,
		STATISTICS_NORECOMPUTE = OFF,
		SORT_IN_TEMPDB = OFF,
		IGNORE_DUP_KEY = OFF,
		ONLINE = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON,
		OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF
	) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Discounts]
ADD DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[Orders]
ADD DEFAULT (getdate()) FOR [OrderDate]
GO
ALTER TABLE [dbo].[PaymentMethods]
ADD DEFAULT ((0)) FOR [Active]
GO
ALTER TABLE [dbo].[Reviews]
ADD DEFAULT (getdate()) FOR [CreateAt]
GO
ALTER TABLE [dbo].[ShippingProviders]
ADD CONSTRAINT [DF__ShippingP__IsAct__019E3B86] DEFAULT ((1)) FOR [IsActive]
GO
ALTER TABLE [dbo].[Users]
ADD CONSTRAINT [DF__Users__Active__7E02B4CC] DEFAULT ((1)) FOR [Active]
GO
ALTER TABLE [dbo].[Books] WITH CHECK
ADD CONSTRAINT [FK__Books__AuthorId__51EF2864] FOREIGN KEY([AuthorId]) REFERENCES [dbo].[Authors] ([AuthorID])
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK__Books__AuthorId__51EF2864]
GO
ALTER TABLE [dbo].[Books] WITH CHECK
ADD CONSTRAINT [FK__Books__CategoryI__52E34C9D] FOREIGN KEY([CategoryId]) REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK__Books__CategoryI__52E34C9D]
GO
ALTER TABLE [dbo].[Books] WITH CHECK
ADD CONSTRAINT [FK__Books__Publisher__53D770D6] FOREIGN KEY([PublisherId]) REFERENCES [dbo].[Publishers] ([PublisherID])
GO
ALTER TABLE [dbo].[Books] CHECK CONSTRAINT [FK__Books__Publisher__53D770D6]
GO
ALTER TABLE [dbo].[Carts] WITH CHECK
ADD FOREIGN KEY([BookID]) REFERENCES [dbo].[Books] ([BookId])
GO
ALTER TABLE [dbo].[Carts] WITH CHECK
ADD FOREIGN KEY([UserID]) REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Favorites] WITH CHECK
ADD CONSTRAINT [FK_Favorites_Books] FOREIGN KEY([BookID]) REFERENCES [dbo].[Books] ([BookId])
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Books]
GO
ALTER TABLE [dbo].[Favorites] WITH CHECK
ADD CONSTRAINT [FK_Favorites_Users] FOREIGN KEY([UserID]) REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Users]
GO
ALTER TABLE [dbo].[OrderDetails] WITH CHECK
ADD FOREIGN KEY([BookID]) REFERENCES [dbo].[Books] ([BookId])
GO
ALTER TABLE [dbo].[OrderDetails] WITH CHECK
ADD FOREIGN KEY([OrderID]) REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[Orders] WITH CHECK
ADD FOREIGN KEY([DiscountID]) REFERENCES [dbo].[Discounts] ([DiscountID])
GO
ALTER TABLE [dbo].[Orders] WITH CHECK
ADD FOREIGN KEY([PaymentMethodID]) REFERENCES [dbo].[PaymentMethods] ([PaymentMethodID])
GO
ALTER TABLE [dbo].[Orders] WITH CHECK
ADD FOREIGN KEY([ProviderID]) REFERENCES [dbo].[ShippingProviders] ([ProviderID])
GO
ALTER TABLE [dbo].[Orders] WITH CHECK
ADD FOREIGN KEY([UserID]) REFERENCES [dbo].[Users] ([UserId])
GO
ALTER TABLE [dbo].[Reviews] WITH CHECK
ADD FOREIGN KEY([BookID]) REFERENCES [dbo].[Books] ([BookId])
GO
ALTER TABLE [dbo].[Reviews] WITH CHECK
ADD FOREIGN KEY([OrderID]) REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[Reviews] WITH CHECK
ADD FOREIGN KEY([UserID]) REFERENCES [dbo].[Users] ([UserId])
GO